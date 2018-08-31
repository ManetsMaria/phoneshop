package com.es.phoneshop.web.controller.ajax;

import validation.cart.CartFormItem;
import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.phoneshop.web.controller.pages.service.OutOfStockValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class AjaxCartController {
    @Resource
    private CartService cartService;
    @Resource
    private OutOfStockValidationService outOfStockValidationService;

    @RequestMapping(method = RequestMethod.POST, value = "/ajaxCart")
    public @ResponseBody
    AjaxReturnEntity addPhone(@Valid CartFormItem cartFormItem, BindingResult bindingResult){
        AjaxReturnEntity ajaxReturnEntity = new AjaxReturnEntity();
        if (bindingResult.hasErrors()){
            ajaxReturnEntity.setErrorMessage("input number equal or greater than 1");
            return ajaxReturnEntity;
        }
        if (!outOfStockValidationService.validAddInput(cartFormItem.getPhoneId(), cartFormItem.getQuantity())){
            ajaxReturnEntity.setErrorMessage("out of stock");
            return ajaxReturnEntity;
        }
        cartService.addPhone(cartFormItem.getPhoneId(), cartFormItem.getQuantity());
        Cart cart = cartService.getCart();
        ajaxReturnEntity.setQuantity(cart.getQuantity());
        ajaxReturnEntity.setSumma(cart.getSumma());
        return ajaxReturnEntity;


        /*if (bindingResult.hasErrors()) {
            return "false;input number equal or greater than 1";
        }
        if (!outOfStockValidationService.validAddInput(cartFormItem.getPhoneId(), cartFormItem.getQuantity())){
            return "false;out of stock";
        }
        cartService.addPhone(cartFormItem.getPhoneId(), cartFormItem.getQuantity());
        Cart cart = cartService.getCart();
        return "true;"+cart.getQuantity()+";"+cart.getSumma(); */


        /*try{
            Long quantityNumber = inputQuantityTrans.getQuantity(quantity, phoneId);
            cartService.addPhone(phoneId, quantityNumber);
            Cart cart = cartService.getCart();
            return "true;"+cart.getQuantity()+";"+cart.getSumma();
        } catch (QuantityException e) {
            return "false;not enough phones on stock";
        } catch (NumberFormatException e){
            return "false;only numbers";
        }*/
    }
}
