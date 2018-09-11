package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.model.order.Order;
import com.es.core.order.OrderService;
import com.es.core.order.OutOfStockException;
import com.es.phoneshop.web.controller.pages.service.FillUnknowOrderFieldsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import validation.order.OrderForm;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {
    @Resource
    private OrderService orderService;
    @Resource
    private CartService cartService;
    @Resource
    private FillUnknowOrderFieldsService fillUnknowOrderFieldsService;

    private final String ORDER_FORM = "orderForm";
    private final String ORDER = "order";

    @RequestMapping(method = RequestMethod.GET)
    public String getOrder(Model model){
        Order order = orderService.createOrder(cartService.getCart());
        model.addAttribute(ORDER, order);
        model.addAttribute(ORDER_FORM, new OrderForm());
        return ORDER;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String placeOrder(@ModelAttribute(ORDER_FORM) @Valid OrderForm orderForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        //System.out.println(bindingResult.getModel());
        //fillUnknowOrderFieldsService.fillPhoneColors(order);
        //fillUnknowOrderFieldsService.fillPrices(order);
        /*Map<Long, Long> phones = cartService.getCart().getPhones();
        phones.put(1006L, 1000L);
        cartService.getCart().setPhones(phones); */
        Order order = orderService.createOrder(cartService.getCart());
        fillUnknowOrderFieldsService.fillOrderWithOrderForm(order, orderForm);
        model.addAttribute(ORDER, order);
        if(bindingResult.hasErrors()){
            return ORDER;
        }
        //order.getOrderItems().get(1).setQuantity(100L);
        try {
            orderService.placeOrder(order);
            cartService.removeAll();
            //redirectAttributes.addFlashAttribute(ORDER_FORM, orderForm);
            redirectAttributes.addFlashAttribute(ORDER, order);
            return "redirect:/orderOverview";
        } catch (OutOfStockException e) {
            bindingResult.rejectValue("outOfStock", "outOfStock");
            return ORDER;
        }
    }
}
