package com.es.core.model.phone;

import com.es.core.model.phone.color.Color;
import com.es.core.action.sort.Specification;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PhoneDao {
    Optional<Phone> get(Long key);
    void save(Phone phone);
    List<Phone> findAll(int offset, int limit, Specification sortCriteria);
    Set<Color> getColorByPhoneId(long phoneId);
    int getPhoneNumberContainsOnStock();
    Optional<Stock> getStockByPhoneId(long phoneId);
    void setSearch(String search);
    String getSearch();
    List<Phone> getPhoneListById(Set<Long> setId);
}
