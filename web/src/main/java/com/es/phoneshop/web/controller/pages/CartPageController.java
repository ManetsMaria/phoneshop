package com.es.phoneshop.web.controller.pages;

import validation.cart.CartForm;
import com.es.core.cart.CartService;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.phoneshop.web.controller.pages.service.OutOfStockValidationService;
import com.es.phoneshop.web.controller.pages.service.QuantityListConverterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    @Resource
    private CartService cartService;
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private QuantityListConverterService quantityListConverterService;
    @Resource
    private OutOfStockValidationService outOfStockValidationService;

    private final String PHONES = "phones";
    private final String CART_FORM = "cartForm";

    @RequestMapping(method = RequestMethod.GET)
    public String getCart(Model model) {
        List<Phone> phones = phoneDao.getPhoneListById(cartService.getCart().getPhones().keySet());
        CartForm cartForm = quantityListConverterService.getQuantityFieldsByPhoneList(phones);
        model.addAttribute(PHONES, phones);
        model.addAttribute(this.CART_FORM, cartForm);
        return "cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateCart(@ModelAttribute(CART_FORM) @Valid CartForm cartForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors() || !outOfStockValidationService.validUpdateInput(cartForm, bindingResult)) {
            model.addAttribute(PHONES, phoneDao.getPhoneListById(cartService.getCart().getPhones().keySet()));
            return "cart";
        }
        cartService.update(quantityListConverterService.getMapByQuantityFields(cartForm));
        return "redirect:/cart";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String deleteItemFromCart(long id) {
        cartService.remove(id);
        return "redirect:/cart";
    }
}
