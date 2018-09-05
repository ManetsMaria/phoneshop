package com.es.phoneshop.web.controller.ajax;

import validation.cart.CartFormItem;
import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.phoneshop.web.controller.pages.service.OutOfStockValidationInputService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Properties;

@Controller
public class AjaxCartController {
    @Resource
    private CartService cartService;
    @Resource
    private OutOfStockValidationInputService outOfStockValidationInputService;
    @Resource
    private Properties messages;

    @RequestMapping(method = RequestMethod.POST, value = "/ajaxCart")
    public @ResponseBody
    AddToCartResult addPhone(@Valid CartFormItem cartFormItem, BindingResult bindingResult){
        AddToCartResult addToCartResult = new AddToCartResult();
        if (bindingResult.hasErrors()){
            addToCartResult.setErrorMessage(messages.getProperty(bindingResult.getAllErrors().get(0).getCode()));
            return addToCartResult;
        }
        if (!outOfStockValidationInputService.validAddInput(cartFormItem.getPhoneId(), cartFormItem.getQuantity())){
            addToCartResult.setErrorMessage("out of stock");
            return addToCartResult;
        }
        cartService.addPhone(cartFormItem.getPhoneId(), cartFormItem.getQuantity());
        Cart cart = cartService.getCart();
        addToCartResult.setQuantity(cart.getQuantity());
        addToCartResult.setSumma(cart.getSumma());
        return addToCartResult;
    }
}
