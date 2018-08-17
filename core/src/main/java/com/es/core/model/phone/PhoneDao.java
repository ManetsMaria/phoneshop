package com.es.core.model.phone;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PhoneDao {
    Optional<Phone> get(Long key);
    void save(Phone phone);
    List<Phone> findAll(int offset, int limit);
    Set<Color> getColorByPhoneId(long phoneId);
    int getPhoneNumberContainsOnStock();
}
