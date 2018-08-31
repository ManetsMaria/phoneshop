package com.es.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import com.es.core.action.sort.Specification;
import com.es.phoneshop.web.controller.pages.service.PageCounterService;
import com.es.phoneshop.web.controller.pages.service.SpecificationConverterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.es.core.model.phone.PhoneDao;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping (value = "/productList")
public class ProductListPageController {
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private PageCounterService pageCounterService;
    @Resource
    private SpecificationConverterService specificationConverterService;

    Specification current;

    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(Model model, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int itemsOnPage, @RequestParam(required = false) String orderId, @RequestParam(required = false) String search) { //name doesn't matter
        phoneDao.setSearch(search);
        int currentPage = Math.max(1, page);
        int pageCount = pageCounterService.calcPageCount(itemsOnPage);
        currentPage = Math.min(pageCount, currentPage);
        current = specificationConverterService.getBestVariant(current, specificationConverterService.getSpecificationById(orderId));
        model.addAttribute("phones", phoneDao.findAll((currentPage - 1)*itemsOnPage, itemsOnPage, current));
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("search", phoneDao.getSearch());
        //quantityField.setQuantity(1L);
        //model.addAttribute("quantityField", quantityField);
        /*String disabledNext = "";
        if ((phoneDao.findAll(currentPage*itemsOnPage, 1).isEmpty())) {
            disabledNext = "disabled";
        }
        model.addAttribute("disabledNext", disabledNext);*/
        return "productList"; //name of jsp that config like dispatcher-servlet.xml say
    }
}
