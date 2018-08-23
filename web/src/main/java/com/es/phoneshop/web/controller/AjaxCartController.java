package com.es.phoneshop.web.controller;

import com.es.core.action.validation.QuantityField;
import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.exception.QuantityException;
import com.es.phoneshop.web.controller.pages.service.InputQuantityTrans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class AjaxCartController {
    @Resource
    private CartService cartService;
    @Resource
    private InputQuantityTrans inputQuantityTrans;

    @RequestMapping(method = RequestMethod.POST, value = "/ajaxCheck")
    public @ResponseBody String checkInput(@RequestParam @Valid QuantityField quantityField){
        System.out.print(quantityField);
        return "true";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ajaxCart")
    public @ResponseBody
    String addPhone(@RequestParam ("phoneId") Long phoneId, @RequestParam("quantity") String quantity){
        try{
            Long quantityNumber = inputQuantityTrans.getQuantity(quantity, phoneId);
            cartService.addPhone(phoneId, quantityNumber);
            Cart cart = cartService.getCart();
            return "true;"+cart.getQuantity()+";"+cart.getSumma();
        } catch (QuantityException | NumberFormatException e) {
            return "false";
        }
    }
}
