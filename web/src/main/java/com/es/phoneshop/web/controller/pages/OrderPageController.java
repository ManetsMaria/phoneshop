package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.model.order.Order;
import com.es.core.order.OrderService;
import com.es.core.order.OutOfStockException;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import validation.order.OrderForm;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {
    @Resource
    private OrderService orderService;
    @Resource
    private CartService cartService;

    private final String ORDER_FORM = "orderForm";
    private final String ORDER = "order";

    @RequestMapping(method = RequestMethod.GET)
    public String getOrder(Model model, HttpSession session){
        Order order = orderService.createOrder(cartService.getCart());
        model.addAttribute(ORDER_FORM, new OrderForm());
        session.setAttribute(ORDER, order);
        return ORDER;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String placeOrder(@ModelAttribute(ORDER_FORM) @Valid OrderForm orderForm, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            return ORDER;
        }
        //((Order)session.getAttribute(ORDER)).getOrderItems().get(0).setQuantity(100L);
        try {
            orderService.placeOrder((Order)session.getAttribute(ORDER));
            cartService.getCart().removeAll();
            redirectAttributes.addFlashAttribute(ORDER_FORM, orderForm);
            return "redirect:/orderOverview";
        } catch (OutOfStockException e) {
            bindingResult.rejectValue("outOfStock", "outOfStock");
            return ORDER;
        }
    }
}
