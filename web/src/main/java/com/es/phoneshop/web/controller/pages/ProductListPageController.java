package com.es.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import com.es.core.model.phone.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.es.core.model.phone.PhoneDao;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping (value = "/productList")
public class ProductListPageController {
    @Resource
    private PhoneDao phoneDao;

    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(Model model, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int itemsOnPage) { //name doesn't matter
        int currentPage = Math.max(1, page);
        int phoneNumberContainsOnStock = phoneDao.getPhoneNumberContainsOnStock();
        int pageCount = phoneNumberContainsOnStock/itemsOnPage;
        if (phoneNumberContainsOnStock%itemsOnPage != 0){
            pageCount++;
        }
        currentPage = Math.min(pageCount, currentPage);
        model.addAttribute("phones", phoneDao.findAll((currentPage - 1)*itemsOnPage, itemsOnPage));
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageCount", pageCount);
        /*String disabledNext = "";
        if ((phoneDao.findAll(currentPage*itemsOnPage, 1).isEmpty())) {
            disabledNext = "disabled";
        }
        model.addAttribute("disabledNext", disabledNext);*/
        return "productList"; //name of jsp that config like dispatcher-servlet.xml say
    }
}
