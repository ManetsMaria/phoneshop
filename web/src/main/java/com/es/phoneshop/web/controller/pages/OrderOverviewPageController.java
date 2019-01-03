package com.es.phoneshop.web.controller.pages;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderStatus;
import com.es.core.order.OrderService;
import com.es.phoneshop.web.controller.pages.service.CheckStatusService;
import com.es.phoneshop.web.controller.pages.service.ConvertIdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import validation.order.OrderForm;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class OrderOverviewPageController {
    @Resource
    private OrderService orderService;
    @Resource
    private ConvertIdService convertIdService;

    @RequestMapping("order/{orderId}")
    public String getOrderOverview(@PathVariable(value="orderId") Integer orderId, Model model){
        Order order = orderService.getOrderById(convertIdService.getRealBySecret(orderId));
        model.addAttribute("order", order);
        //model.addAttribute(orderForm);
        //model.addAttribute("isAdmin", false);
        return "orderOverview";
    }
}

