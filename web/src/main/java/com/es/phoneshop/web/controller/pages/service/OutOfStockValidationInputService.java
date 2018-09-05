package com.es.phoneshop.web.controller.pages.service;

import com.es.core.model.stock.StockService;
import validation.cart.CartForm;
import validation.cart.CartFormItem;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;

@Service
public class OutOfStockValidationInputService {

    @Resource
    private StockService stockService;

    public boolean validAddInput(Long phoneId, Long quantity){
        if (quantity == null || phoneId == null){
            return false;
        }
        return stockService.checkAdd(phoneId, quantity);
    }

    public boolean validUpdateInput(Long phoneId, Long quantity){
        if (quantity == null || phoneId == null){
            return false;
        }
        return stockService.checkUpdate(phoneId, quantity);
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
