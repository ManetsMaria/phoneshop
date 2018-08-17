package com.es.core.model.phone;

import java.util.Optional;
import java.util.Set;

public interface ColorDao {
    Optional<Color> get(Long key);
    void save(Color color);
}
