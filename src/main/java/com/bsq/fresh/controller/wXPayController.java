package com.bsq.fresh.controller;


import com.bsq.fresh.Vo.ResultVO;
import com.bsq.fresh.dto.OrderDTO;
import com.bsq.fresh.enums.ResultEnum;
import com.bsq.fresh.exception.SellException;
import com.bsq.fresh.service.OrderService;
import com.bsq.fresh.service.PayService;
import com.bsq.fresh.utils.ResultVOUtil;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/pay")
@Slf4j
public class wXPayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;


    @RequestMapping(value="/wxPay",method =RequestMethod.POST)
    public ResultVO<Map<String,Object>> create(@RequestParam("orderId") String orderId
                                               ) {
        //1. 获的微信用户的openid查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2. 用户下单后发起支付，拉起微信的支付软件
        PayResponse payResponse = payService.create(orderDTO);
        return ResultVOUtil.success(payResponse);

    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @RequestMapping(value="/wxNotify",method =RequestMethod.POST)
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }

}
