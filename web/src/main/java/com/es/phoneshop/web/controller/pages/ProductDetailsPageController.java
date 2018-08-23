package com.es.phoneshop.web.controller.pages;

import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {
    @Resource
    PhoneDao phoneDao;
    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(Model model, Long phoneId) { //name doesn't matter
        model.addAttribute("phone", phoneDao.get(phoneId).get());
        return "productDetails"; //name of jsp that config like dispatcher-servlet.xml say
    }
}
