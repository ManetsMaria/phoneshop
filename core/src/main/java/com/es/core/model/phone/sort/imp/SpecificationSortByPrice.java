package com.es.core.model.phone.sort.imp;

import com.es.core.model.phone.sort.Specification;

public class SpecificationSortByPrice implements Specification {
    @Override
    public String sqlOrderBy() {
        return "ORDER BY price";
    }
}
