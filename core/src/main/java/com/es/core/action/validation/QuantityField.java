package com.es.core.action.validation;

public class QuantityField {
    @Quantity
    private String quantity;

    private Long phoneId;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    @Override
    public String toString() {
        return "QuantityField{" +
                "quantity='" + quantity + '\'' +
                ", phoneId=" + phoneId +
                '}';
    }
}
