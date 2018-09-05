package com.es.core.model.color;

import java.util.Optional;

public interface ColorDao {
    Optional<Color> get(Long key);
    void save(Color color);
}
