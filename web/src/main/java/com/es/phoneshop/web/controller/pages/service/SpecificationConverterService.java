package com.es.phoneshop.web.controller.pages.service;

import com.es.core.action.sort.Specification;
import com.es.core.action.sort.imp.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class SpecificationConverterService {
    private final String PRICE_INC = "price_inc";
    private final String BRAND_INC = "brand_inc";
    private final String MODEL_INC = "model_inc";
    private final String SCREEN_INC = "screen_inc";
    private final String PRICE_DEC = "price_dec";
    private final String BRAND_DEC = "brand_dec";
    private final String MODEL_DEC = "model_dec";
    private final String SCREEN_DEC = "screen_dec";

    private Specification specification;

    public Specification getSpecificationById(String id){
        if(id == null){
            return null;
        }
        switch (id){
            case PRICE_INC:
                return new SpecificationSortByPriceInc();
            case BRAND_INC:
                return new SpecificationSortByBrandInc();
            case MODEL_INC:
                return new SpecificationSortByModelInc();
            case SCREEN_INC:
                return new SpecificationSortByScreenSizeInc();
            case PRICE_DEC:
                return new SpecificationSortByPriceDec();
            case BRAND_DEC:
                return new SpecificationSortByBrandDec();
            case SCREEN_DEC:
                return new SpecificationSortByScreenSizeDec();
            case MODEL_DEC:
                return new SpecificationSortByModelDec();
            default:
                return null;
        }
    }

    public void setSpecification(Specification specification){
        if (specification != null){
            this.specification = specification;
        }
    }

    public String getPRICE_INC() {
        return PRICE_INC;
    }

    public String getBRAND_INC() {
        return BRAND_INC;
    }

    public String getMODEL_INC() {
        return MODEL_INC;
    }

    public String getSCREEN_INC() {
        return SCREEN_INC;
    }

    public String getPRICE_DEC() {
        return PRICE_DEC;
    }

    public String getBRAND_DEC() {
        return BRAND_DEC;
    }

    public String getMODEL_DEC() {
        return MODEL_DEC;
    }

    public String getSCREEN_DEC() {
        return SCREEN_DEC;
    }

    public Specification getSpecification() {
        return specification;
    }
}
