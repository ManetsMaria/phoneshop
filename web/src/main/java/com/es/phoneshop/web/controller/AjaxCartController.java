package com.es.phoneshop.web.controller;

import com.es.core.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void addPhone(@RequestParam ("phoneId") Long phoneId, @RequestParam("quantity") Long quantity) {
        System.out.println(phoneId + " " + quantity);
        cartService.addPhone(phoneId, quantity);
    }
}
