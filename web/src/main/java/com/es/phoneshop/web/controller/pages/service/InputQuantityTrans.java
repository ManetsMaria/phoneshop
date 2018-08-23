package com.es.phoneshop.web.controller.pages.service;

import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.exception.QuantityException;
import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InputQuantityTrans {
    @Resource
    private CartService cartService;
    @Resource
    private PhoneDao phoneDao;

    public Long getQuantity(String input, Long phoneId) throws NumberFormatException, QuantityException {
        Long quantity = Long.valueOf(input);
        int commonQuantity = phoneDao.getStockByPhoneId(phoneId).get().getStock();
        Cart cart = cartService.getCart();
        Long currentQuantity = cart.getPhoneQuantity(phoneId);
        if (currentQuantity + quantity > commonQuantity){
            throw new QuantityException("quantity bigger than stock");
        }
        return quantity;
    }
}
