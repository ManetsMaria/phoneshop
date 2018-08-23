package com.es.phoneshop.web.controller.pages.service;

import com.es.core.action.sort.Specification;
import com.es.core.action.sort.imp.*;
import org.springframework.stereotype.Service;

@Service
public class SpecificationConverterService {
    private final int PRICE_INC = 0;
    private final int BRAND_INC = 1;
    private final int MODEL_INC = 2;
    private final int SCREEN_INC = 3;
    private final int PRICE_DEC = 4;
    private final int BRAND_DEC = 5;
    private final int MODEL_DEC = 6;
    private final int SCREEN_DEC = 7;

    public Specification getSpecificationById(int id){
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

    public Specification getBestVariant(Specification currentSpecification, Specification getSpecification){
        if (getSpecification != null){
            return getSpecification;
        }
        return currentSpecification;
    }

    public int getPRICE_INC() {
        return PRICE_INC;
    }

    public int getBRAND_INC() {
        return BRAND_INC;
    }

    public int getMODEL_INC() {
        return MODEL_INC;
    }

    public int getSCREEN_INC() {
        return SCREEN_INC;
    }

    public int getPRICE_DEC() {
        return PRICE_DEC;
    }

    public int getBRAND_DEC() {
        return BRAND_DEC;
    }

    public int getMODEL_DEC() {
        return MODEL_DEC;
    }

    public int getSCREEN_DEC() {
        return SCREEN_DEC;
    }
}
