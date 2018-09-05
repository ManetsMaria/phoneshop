package com.es.core.model.phone;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.es.core.model.color.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class BeanColorParsingRowMapper implements RowMapper {
    @Autowired
    private JdbcPhoneDao jdbcPhoneDao; //cycle: depend from jdbc, that depends from mapper (I mean @Autowired using)

    public Phone mapRow(ResultSet rs, int rowNum) throws SQLException {
        Phone phone = new Phone();
        long id = rs.getLong("id");
        phone.setId(id);
        phone.setBrand(rs.getString("brand"));
        phone.setModel(rs.getString("model"));
        phone.setPrice(rs.getBigDecimal("price"));
        phone.setImageUrl(rs.getString("imageUrl"));
        phone.setDisplaySizeInches(rs.getBigDecimal("displaySizeInches"));
        Set<Color> set = jdbcPhoneDao.getColorByPhoneId(id);
        phone.setColors(set);
        return phone;
    }
}
