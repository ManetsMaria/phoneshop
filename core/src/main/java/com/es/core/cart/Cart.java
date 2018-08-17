package com.es.core.cart;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class Cart { //singleton
    private BigDecimal summa;
    private long quantity;
    private Map<Long, Long> phones;

   /* private static Cart instance;

    private Cart(){
        summa = new BigDecimal(0);
        quantity = 0;
        phones = new HashMap<>();
    }

    public static Cart getInstance(){
        if (instance == null){
            instance = new Cart();
        }
        return instance;
    }*/

    public Cart(){
        summa = new BigDecimal(0);
        quantity = 0;
        phones = new HashMap<>();
    }

    public void addPhone(Long phoneId, Long quantity, BigDecimal price){
        if (!phones.containsKey(phoneId)){
            phones.put(phoneId, 0L);
        }
        phones.put(phoneId, phones.get(phoneId) + quantity);
        this.quantity += quantity;
        price = price.multiply(new BigDecimal(quantity));
        summa = summa.add(price);
    }

    public BigDecimal getSumma() {
        return summa;
    }

    public long getQuantity() {
        return quantity;
    }

    public Map<Long, Long> getPhones() {
        Map<Long, Long> clone = new HashMap<>(phones);
        return clone;
    }
}
