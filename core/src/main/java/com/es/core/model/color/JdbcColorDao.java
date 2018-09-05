package com.es.core.model.color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JdbcColorDao implements ColorDao {
    @Autowired //do not work tests when @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Color> get(Long key) {
        String sql = "select code from colors where id = ?";
        Color color = new Color();
        String colorName = jdbcTemplate.queryForObject(sql, new Object[] { key }, String.class);
        color.setCode(colorName);
        return Optional.of(color);
    }

    @Override
    public void save(Color color) {
        throw new UnsupportedOperationException("TODO");
    }
}
