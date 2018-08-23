package com.es.core.model.phone.color;

import java.util.Optional;

public interface ColorDao {
    Optional<Color> get(Long key);
    void save(Color color);
}
