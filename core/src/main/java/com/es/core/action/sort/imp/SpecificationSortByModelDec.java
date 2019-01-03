package com.es.core.action.sort.imp;

import com.es.core.action.sort.Specification;

public class SpecificationSortByModelDec implements Specification {
    @Override
    public String sqlOrderBy() {
        return "ORDER BY model DESC";
    }
}
