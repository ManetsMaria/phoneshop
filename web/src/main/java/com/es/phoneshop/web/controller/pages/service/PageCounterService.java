package com.es.phoneshop.web.controller.pages.service;

import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PageCounterService {
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private SearchConverterService searchConverterService;

    public int calcPageCount(int itemsOnPage){
        int phoneNumberContainsOnStock = phoneDao.getPhoneNumberContainsOnStock(searchConverterService.constructSearch());
        int pageCount = phoneNumberContainsOnStock/itemsOnPage;
        if (phoneNumberContainsOnStock%itemsOnPage != 0){
            pageCount++;
        }
        return pageCount;
    }
}
