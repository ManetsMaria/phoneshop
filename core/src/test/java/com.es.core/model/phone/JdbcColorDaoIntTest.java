package com.es.core.model.phone;

import com.es.core.model.phone.color.Color;
import com.es.core.model.phone.color.JdbcColorDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"/context/applicationContext-core.xml", "/context/applicationContext-test.xml"})
public class JdbcColorDaoIntTest {
    @Autowired
    JdbcColorDao jdbcColorDao;

    @Test
    public void testGet(){
        Optional<Color> color = jdbcColorDao.get(1000L);
        Assert.assertEquals(color.get().getCode(), "Black");
    }

    @Test (expected = EmptyResultDataAccessException.class)
    public void testGetUnreal(){
        Optional<Color> color = jdbcColorDao.get(1007L);
    }

}
