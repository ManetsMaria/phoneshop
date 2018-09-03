package com.es.phoneshop.web.controller.pages.service;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class CheckStatusService {

    public void setStatus(Order order, String orderStatus){
        if("DELIVERED".equals(orderStatus)) {
            order.setStatus(OrderStatus.DELIVERED);
        }
        if("REJECTED".equals(orderStatus)) {
            order.setStatus(OrderStatus.REJECTED);
        }
    }
}
