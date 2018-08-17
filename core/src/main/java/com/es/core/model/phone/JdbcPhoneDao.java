package com.es.core.model.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JdbcPhoneDao implements PhoneDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BeanColorParsingRowMapper beanColorParsingRowMapper;
    @Autowired
    private JdbcColorDao jdbcColorDao;

    public Optional<Phone> get(final Long key) {
        String sql = "select * from phones where id = ?";
        Phone phone = jdbcTemplate.queryForObject(sql, new Object[] { key }, Phone.class);
        return Optional.of(phone);
    }

    public void save(final Phone phone) {
        throw new UnsupportedOperationException("TODO");
    }

    public List<Phone> findAll(int offset, int limit) {
        return jdbcTemplate.query("select phones.* from (phones inner join stocks on id = phoneId) where (stock > 0 AND price is not null)offset " + offset + " limit " + limit, beanColorParsingRowMapper);
    }

    @Override
    public Set<Color> getColorByPhoneId(long phoneId) {
        String sql = "select colorId from phone2color where phoneId = ?";
        List<Long> keys = jdbcTemplate.queryForList(sql, new Object[] { phoneId }, Long.class);
        return keys.stream().map(jdbcColorDao::get).map(Optional::get).collect(Collectors.toSet());
    }

    @Override
    public int getPhoneNumberContainsOnStock() {
        String sql = "select Count(phones.*) from (phones inner join stocks on id = phoneId) where (stock > 0 and price is not null)";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
