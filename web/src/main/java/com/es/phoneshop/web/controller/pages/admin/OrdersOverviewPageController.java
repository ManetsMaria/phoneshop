package com.es.phoneshop.web.controller.pages.admin;

import com.es.core.model.order.Order;
import com.es.core.order.OrderService;
import com.es.phoneshop.web.controller.pages.service.CheckStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class OrdersOverviewPageController {

    @Resource
    private CheckStatusService checkStatusService;
    @Resource
    private OrderService orderService;

    @RequestMapping("admin/orders/{orderId}")
    public String getOrderOverviewAdmin(@PathVariable("orderId") long orderId, @RequestParam(required = false) String orderStatus, Model model){
        Order order = orderService.getOrderById(orderId);
        checkStatusService.setStatus(order, orderStatus);
        model.addAttribute("order", order);
        //model.addAttribute("isAdmin", true);
        return "adminOrderOverview";
    }
}
