package com.es.phoneshop.web.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import validation.order.OrderForm;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/orderOverview")
public class OrderOverviewPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String getOrderOverview(@ModelAttribute("orderForm")OrderForm orderForm, Model model){
        model.addAttribute(orderForm);
        return "orderOverview";
    }
}

