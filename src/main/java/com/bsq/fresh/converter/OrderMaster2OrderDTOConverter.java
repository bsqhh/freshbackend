package com.bsq.fresh.converter;



import com.bsq.fresh.dto.AddRessDTo;
import com.bsq.fresh.dto.OrderDTO;
import com.bsq.fresh.entity.AddRess;
import com.bsq.fresh.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }


    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }

    public static AddRessDTo convertRess(AddRess addRess) {

        AddRessDTo addRessDTo = new AddRessDTo();
        BeanUtils.copyProperties(addRess, addRessDTo);
        return addRessDTo;
    }

    public static List<AddRessDTo> convertRess(List<AddRess> addRessList) {
        return addRessList.stream().map(e ->
                convertRess(e)
        ).collect(Collectors.toList());
    }
}
