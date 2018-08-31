package com.es.phoneshop.web.controller.ajax;

import java.math.BigDecimal;

public class AjaxReturnEntity {
    private Long quantity;
    private BigDecimal summa;
    private String errorMessage;

    public Long getQuantity() {
        return quantity;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BigDecimal getSumma() {
        return summa;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }
}
