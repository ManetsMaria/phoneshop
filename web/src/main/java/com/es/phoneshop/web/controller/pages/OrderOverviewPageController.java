package com.es.phoneshop.web.controller.pages;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderStatus;
import com.es.core.order.OrderService;
import com.es.phoneshop.web.controller.pages.service.CheckStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import validation.order.OrderForm;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class OrderOverviewPageController {
    @Resource
    private OrderService orderService;
    @Resource
    private CheckStatusService checkStatusService;

    @RequestMapping(method = RequestMethod.GET, value = "/orderOverview")
    public String getOrderOverview(@ModelAttribute("order") Order order){
        //model.addAttribute(orderForm);
        //model.addAttribute("isAdmin", false);
        return "orderOverview";
    }

    @RequestMapping(method = RequestMethod.GET, value = "admin/orderOverview")
    public String getOrderOverviewAdmin(@RequestParam("orderId") long orderId, @RequestParam(required = false) String orderStatus, Model model){
        Order order = orderService.getOrderById(orderId);
        checkStatusService.setStatus(order, orderStatus);
        model.addAttribute("order", order);
        //model.addAttribute("isAdmin", true);
        return "adminOrderOverview";
    }
}

