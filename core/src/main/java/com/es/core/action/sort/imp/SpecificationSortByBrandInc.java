package com.es.core.action.sort.imp;

import com.es.core.action.sort.Specification;

public class SpecificationSortByBrandInc implements Specification {
    @Override
    public String sqlOrderBy() {
        return "ORDER BY brand";
    }
}
