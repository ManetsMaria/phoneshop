package com.es.phoneshop.web.controller.pages.service;

import validation.cart.CartForm;
import com.es.core.cart.CartService;
import com.es.core.model.phone.Phone;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuantityListConverterService {
    @Resource
    private CartService cartService;

    public CartForm getQuantityFieldsByPhoneList(List<Phone> phones){
        CartForm cartForm = new CartForm();
        phones.forEach(phone -> cartForm.addQuantityField(phone.getId(), cartService.getCart().getPhoneQuantity(phone.getId())));
        return cartForm;
    }

    public Map<Long, Long> getMapByQuantityFields(CartForm cartForm){
        Map<Long, Long> phoneWithQuantity = new HashMap<>();
        cartForm.getCartFormItems().forEach(q -> phoneWithQuantity.put(q.getPhoneId(), q.getQuantity()));
        return phoneWithQuantity;
    }
}
