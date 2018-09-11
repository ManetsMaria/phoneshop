package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class InitController {

    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET, value = "/header")
    public String initCart(Model model){
        model.addAttribute("cartService", cartService);
        return "header";
    }
}
