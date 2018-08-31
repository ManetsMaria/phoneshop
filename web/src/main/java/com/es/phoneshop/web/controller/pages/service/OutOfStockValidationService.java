package com.es.phoneshop.web.controller.pages.service;

import validation.cart.CartForm;
import validation.cart.CartFormItem;
import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;

@Service
public class OutOfStockValidationService {

    @Resource
    CartService cartService;
    @Resource
    PhoneDao phoneDao;

    public boolean validAddInput(Long phoneId, Long quantity){
        if (quantity == null || phoneId == null){
            return false;
        }
        int commonQuantity = phoneDao.getStockByPhoneId(phoneId).get().getStock();
        Cart cart = cartService.getCart();
        Long currentQuantity = cart.getPhoneQuantity(phoneId);
        if(currentQuantity + quantity > commonQuantity){
            return false;
        }
        return true;
    }

    public boolean validUpdateInput(Long phoneId, Long quantity){
        if (quantity == null || phoneId == null){
            return false;
        }
        int commonQuantity = phoneDao.getStockByPhoneId(phoneId).get().getStock();
        if (quantity > commonQuantity){
            return false;
        }
        return true;
    }

    public boolean validUpdateInput(CartForm cartForm, BindingResult bindingResult){
        boolean flag = true;
        for(int i = 0; i < cartForm.getCartFormItems().size(); i++){
            CartFormItem cartFormItem = cartForm.getCartFormItems().get(i);
            if(!validUpdateInput(cartFormItem.getPhoneId(), cartFormItem.getQuantity())){
                bindingResult.rejectValue("cartFormItems["+i+"].quantity", "out.of.stock");
                flag = false;
            }
        }
        return flag;
    }
}
