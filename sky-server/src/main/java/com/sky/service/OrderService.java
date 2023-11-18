package com.sky.service;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.vo.OrderSubmitVO;
import org.springframework.beans.factory.annotation.Autowired;

public interface OrderService {

    /*
     * 用户下单
     * */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);
}
