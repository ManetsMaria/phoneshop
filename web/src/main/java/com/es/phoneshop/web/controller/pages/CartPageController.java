package com.es.phoneshop.web.controller.pages;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import validation.cart.CartForm;
import com.es.core.cart.CartService;
import com.es.core.model.phone.PhoneDao;
import com.es.phoneshop.web.controller.pages.service.OutOfStockValidationInputService;
import com.es.phoneshop.web.controller.pages.service.QuantityListConverterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    private OutOfStockValidationInputService outOfStockValidationInputService;

    private final String PHONES = "phones";
    private final String CART_FORM = "cartForm";
    private final String UPDATE_SUCCESSFULLY = "updateSuccessfully";

    @RequestMapping(method = RequestMethod.GET)
    public String getCart(Model model, @ModelAttribute(UPDATE_SUCCESSFULLY) String str) {
        CartForm cartForm = quantityListConverterService.getQuantityFieldsByPhoneList(cartService.getCart());
        model.addAttribute(PHONES, phoneDao.getPhoneListById(cartService.getCart().getPhones().keySet()));
        model.addAttribute(this.CART_FORM, cartForm);
        return "cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateCart(@ModelAttribute(CART_FORM) @Valid CartForm cartForm,
                             BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes) {
        boolean isOutOfStock = !outOfStockValidationInputService.validUpdateInput(cartForm, bindingResult);
        if (bindingResult.hasErrors() || isOutOfStock) {
            model.addAttribute(PHONES, phoneDao.getPhoneListById(cartService.getCart().getPhones().keySet()));
            return "cart";
        }
        cartService.update(quantityListConverterService.getMapByQuantityFields(cartForm));
        //model.addAttribute("updateSuccessfully","update successfully");
        //return getCart(model);
        redirectAttributes.addFlashAttribute(UPDATE_SUCCESSFULLY, "update successfully");
        return "redirect:/cart";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String deleteItemFromCart(long id) {
        cartService.remove(id);
        return "redirect:/cart";
    }
}
