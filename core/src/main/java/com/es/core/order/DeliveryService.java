package com.es.core.order;

import java.util.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class DeliveryService {
    @Resource
    private Properties properties;

    public BigDecimal getDelivery(){
        try{
            return new BigDecimal(properties.getProperty("delivery.price"));
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            return new BigDecimal(0);
        }
    }
}
