package com.bsq.fresh.service.impl;



import com.bsq.fresh.converter.OrderMaster2OrderDTOConverter;
import com.bsq.fresh.dao.AddRessRepository;
import com.bsq.fresh.dao.OrderMasterRepositor;
import com.bsq.fresh.dto.AddRessDTo;
import com.bsq.fresh.dto.CartDTO;
import com.bsq.fresh.dto.OrderDTO;
import com.bsq.fresh.dao.OrderDetailRepository;
import com.bsq.fresh.entity.AddRess;
import com.bsq.fresh.entity.CategoryTwoFood;
import com.bsq.fresh.entity.OrderDetail;
import com.bsq.fresh.entity.OrderMaster;
import com.bsq.fresh.enums.OrderStatusEnum;
import com.bsq.fresh.enums.PayStatusEnum;
import com.bsq.fresh.enums.ResultEnum;
import com.bsq.fresh.exception.SellException;
import com.bsq.fresh.service.*;
import com.bsq.fresh.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CategoryTwoFoodService categoryTwoFoodService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepositor orderMasterRepository;

    @Autowired
    private AddRessRepository addRessRepository;

    @Autowired
    private PayService payService;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private WebSocket webSocket;


    //创建订单
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1. 查询商品（数量, 价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            CategoryTwoFood categoryTwoFood = categoryTwoFoodService.findOne(orderDetail.getProductId());
            if (categoryTwoFood == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算订单总价
            orderAmount = categoryTwoFood.getCategoryTwoFoodPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(categoryTwoFood, orderDetail);
            orderDetailRepository.save(orderDetail);
        }
            //3. 写入订单数据库（orderMaster和orderDetail）
            OrderMaster orderMaster = new OrderMaster();
            orderDTO.setOrderId(orderId);
            BeanUtils.copyProperties(orderDTO, orderMaster);
            orderMaster.setOrderAmount(orderAmount);
            orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
            orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
            orderMasterRepository.save(orderMaster);

            //4. 扣库存
            List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                    new CartDTO(e.getProductId(), e.getProductQuantity())
            ).collect(Collectors.toList());
        categoryTwoFoodService.reduceStock(cartDTOList);

//            //发送websocket消息
//             webSocket.sendMessage(orderDTO.getOrderId());

            return orderDTO;

    }


    //查询单个订单
    @Override
    public OrderDTO findOne(String orderId) {
        System.out.println("orderId:"+orderId);
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    //取消订单
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        categoryTwoFoodService.addStock(cartDTOList);

        //如果已支付, 需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
          payService.refund(orderDTO);
        }

        return orderDTO;
    }

    //卖家完结订单
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //推送微信模版消息
        pushMessageService.orderStatus(orderDTO);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    //添加地址
    @Override
    @Transactional
    public AddRessDTo createRess(AddRessDTo addRessDTo)
    {
        String byId = KeyUtil.genUniqueKey();
        AddRess addRess=new AddRess();
        addRessDTo.setById(byId);
        BeanUtils.copyProperties(addRessDTo, addRess);
        addRessRepository.save(addRess);
        return addRessDTo;
    }

    //查询地址
    @Override
    public Page<AddRessDTo> findRessList(String byOpenid,Pageable pageable){
        Page<AddRess> addRessPage = addRessRepository.findByByOpenid(byOpenid, pageable);
        List<AddRessDTo> addRessDToList=OrderMaster2OrderDTOConverter.convertRess(addRessPage.getContent());
        return new PageImpl<AddRessDTo>(addRessDToList,pageable,addRessPage.getTotalElements());

    }
}
