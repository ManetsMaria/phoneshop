package com.es.core.model.order;

import com.es.core.model.phone.Phone;

import java.math.BigDecimal;

public class OrderItem {
    private Long id;
    private Phone phone;
    private BigDecimal subtotal;
    private Long quantity;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(final Phone phone) {
        this.phone = phone;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
