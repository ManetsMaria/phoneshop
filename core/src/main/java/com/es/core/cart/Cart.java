package com.es.core.cart;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class Cart implements Serializable {
    private BigDecimal summa;
    private long quantity;
    private Map<Long, Long> phones;

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

    public Long getPhoneQuantity(Long phoneId){
        Long quantity = phones.get(phoneId);
        if (quantity == null){
            return 0L;
        }
        return quantity;
    }

    public void removePhone(long phoneId, BigDecimal price){
        if (!phones.containsKey(phoneId)){
            return;
        }
        long quantity = phones.get(phoneId);
        price = price.multiply(new BigDecimal(quantity));
        summa = summa.subtract(price);
        this.quantity -= quantity;
        phones.remove(phoneId);
    }

    public void removeAll(){
        summa = new BigDecimal(0);
        quantity = 0L;
        phones = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "summa=" + summa +
                ", quantity=" + quantity +
                ", phones=" + phones +
                '}';
    }
}
