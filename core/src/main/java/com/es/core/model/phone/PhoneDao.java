package com.es.core.model.phone;

import com.es.core.model.color.Color;
import com.es.core.action.sort.Specification;
import com.es.core.model.stock.Stock;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PhoneDao {
    Optional<Phone> get(Long key);
    void save(Phone phone);
    List<Phone> findAll(int offset, int limit, Specification sortCriteria, String search);
    Set<Color> getColorByPhoneId(long phoneId);
    int getPhoneNumberContainsOnStock(String search);
    Optional<Stock> getStockByPhoneId(long phoneId);
    List<Phone> getPhoneListById(Set<Long> setId);
    void updateStock(Long phoneId, Long quantity);
}
