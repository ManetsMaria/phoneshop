package com.es.core.model.phone;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"/context/applicationContext-core.xml"})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "/db/demodataTest.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "/db/deleteAfterTest.sql") })
//@Transactional
public class JdbcPhoneDaoIntTest {
    @Autowired
    JdbcPhoneDao jdbcPhoneDao;

    /*@BeforeClass
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            scripts = "/db/demodataTest.sql")
    public static void init(){}

    @AfterClass
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            scripts = "/db/deleteAfterTest.sql")
    public static void destroy(){}*/

    @Test
    public void testFindAllSingleIdCheck(){
        List<Phone> resultPhones = jdbcPhoneDao.findAll(0, 1);
        long id = resultPhones.get(0).getId();
        Assert.assertEquals(1000L, id);
    }

    @Test
    public void testFindAllMultIdCheck(){
        List<Phone> resultPhones = jdbcPhoneDao.findAll(0, 3);
        List<Long> actualResult = new ArrayList<>();
        resultPhones.forEach(phone -> actualResult.add(phone.getId()));
        Assert.assertEquals(Stream.of(1000L, 1001L, 1002L)
                .collect(Collectors.toList()), actualResult);
    }

    @Test
    public void testFindAllEmpty(){
        List<Phone> resultPhones = jdbcPhoneDao.findAll(0, 0);
        Assert.assertEquals(0, resultPhones.size());
    }

    @Test
    public void testFindAllCornerOffset(){
        List<Phone> resultPhones = jdbcPhoneDao.findAll(10, 0);
        Assert.assertEquals(0, resultPhones.size());
    }

    @Test
    public void testFindAllSingleColorSingleCheck(){
        List<Phone> resultPhones = jdbcPhoneDao.findAll(2, 1);
        List<String> actualResult = new ArrayList<>();
        resultPhones.forEach(phone -> phone.getColors().forEach(color -> actualResult.add(color.getCode())));
        Assert.assertEquals(Stream.of("Yellow").collect(Collectors.toList()), actualResult);
    }

    @Test
    public void testGetWithIdMult(){
        Set<Color> resultColors = jdbcPhoneDao.getColorByPhoneId(1000L);
        Set<String> resultStringColors = new HashSet<>();
        resultColors.forEach((color) -> resultStringColors.add(color.getCode()));
        Assert.assertEquals(resultStringColors, Stream.of("Black", "White")
                .collect(Collectors.toCollection(HashSet::new)));
    }

    @Test
    public void testGetWithIdSingle(){
        Set<Color> resultColors = jdbcPhoneDao.getColorByPhoneId(1004L);
        Set<String> resultStringColors = new HashSet<>();
        resultColors.forEach((color) -> resultStringColors.add(color.getCode()));
        Assert.assertEquals(resultStringColors, Stream.of("Black")
                .collect(Collectors.toCollection(HashSet::new)));
    }

    @Test
    public void testGetWithIdUnreal(){
        Set<Color> resultColors = jdbcPhoneDao.getColorByPhoneId(1007L);
        Set<String> resultStringColors = new HashSet<>();
        resultColors.forEach((color) -> resultStringColors.add(color.getCode()));
        Assert.assertEquals(resultStringColors, Collections.EMPTY_SET);
    }

}
