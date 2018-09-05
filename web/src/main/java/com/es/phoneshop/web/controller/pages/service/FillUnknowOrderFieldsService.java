package com.es.phoneshop.web.controller.pages.service;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FillUnknowOrderFieldsService {

    @Resource
    private PhoneDao phoneDao;

    public void fillPhoneColors(Order order){
        for(OrderItem orderItem: order.getOrderItems()){
            orderItem.getPhone().setColors(phoneDao.getColorByPhoneId(orderItem.getPhone().getId()));
        }
    }
}
