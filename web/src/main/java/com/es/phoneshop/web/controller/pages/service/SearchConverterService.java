package com.es.phoneshop.web.controller.pages.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class SearchConverterService {
    private String search;

    public String constructSearch(){
        if (search == null || search.isEmpty()){
            return "";
        }
        return " and (brand='"+search+"' or model='"+search+"')";
    }

    public void setSearch(String search){
        if (search != null){
            this.search = search;
        }
    }

    public String getSearch() {
        return search;
    }
}
