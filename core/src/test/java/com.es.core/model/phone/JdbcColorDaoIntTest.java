package com.es.core.model.phone;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"/context/applicationContext-core.xml"})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "/db/demodataTest.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "/db/deleteAfterTest.sql") }) //it will be ok without this sql. But i do not change anything and it sees only this data
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
