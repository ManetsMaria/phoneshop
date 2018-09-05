package com.es.core.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@SessionScope
public class Cart implements Serializable {
    private BigDecimal summa;
    private long quantity;
    private Map<Long, Long> phones;

    public Cart(){
        summa = new BigDecimal(0);
        quantity = 0;
        phones = new HashMap<>();
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

    @Override
    public String toString() {
        return "Cart{" +
                "summa=" + summa +
                ", quantity=" + quantity +
                ", phones=" + phones +
                '}';
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setPhones(Map<Long, Long> phones) {
        this.phones = phones;
    }
}
