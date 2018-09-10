package com.es.phoneshop.web.controller.pages.service;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.order.DeliveryService;
import validation.order.OrderForm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class FillUnknowOrderFieldsService {

    @Resource
    private PhoneDao phoneDao;
    @Resource
    private DeliveryService deliveryService;

    public void fillPhoneColors(Order order){
        for(OrderItem orderItem: order.getOrderItems()){
            orderItem.getPhone().setColors(phoneDao.getColorByPhoneId(orderItem.getPhone().getId()));
        }
    }

    public void fillPrices(Order order){
        BigDecimal delivery = deliveryService.getDelivery();
        order.setDeliveryPrice(delivery);
        BigDecimal subTotal = new BigDecimal(0);
        for(OrderItem orderItem: order.getOrderItems()){
            BigDecimal price = phoneDao.get(orderItem.getPhone().getId()).get().getPrice();
            orderItem.getPhone().setPrice(price);
            Long quantity = orderItem.getQuantity();
            subTotal = subTotal.add(price.multiply(new BigDecimal(quantity)));
        }
        order.setSubtotal(subTotal);
        order.setTotalPrice(subTotal.add(delivery));
    }

    public void fillOrderFormFields(Order order, OrderForm orderForm){
        order.setFirstName(orderForm.getFirstName());
        order.setLastName(orderForm.getLastName());
        order.setDeliveryAddress(orderForm.getAddress());
        order.setContactPhoneNo(orderForm.getPhone());
    }
}
