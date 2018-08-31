package com.es.core.model.phone;

import com.es.core.model.phone.color.Color;
import com.es.core.model.phone.color.JdbcColorDao;
import com.es.core.action.sort.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JdbcPhoneDao implements PhoneDao{
//    @Resource
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BeanColorParsingRowMapper beanColorParsingRowMapper;
    //@Autowired
    @Resource
    private JdbcColorDao jdbcColorDao;

    private String search;

    public Optional<Phone> get(final Long key) {
        String sql = "select * from phones where id = ?";
        Phone phone = jdbcTemplate.queryForObject(sql, new Object[] { key }, new BeanPropertyRowMapper<>(Phone.class));
        if (phone != null){
            phone.setColors(getColorByPhoneId(key));
        }
        return Optional.of(phone);
    }

    public void save(final Phone phone) {
        throw new UnsupportedOperationException("TODO");
    }

    public List<Phone> findAll(int offset, int limit, Specification sortCriteria) {
        String order = "";
        if (sortCriteria != null){
            order = sortCriteria.sqlOrderBy();
        }
        return jdbcTemplate.query("select phones.* from (phones inner join stocks on id = phoneId) where (stock > 0 AND price is not null"+consructSearch()+")"+order+" offset " + offset + " limit " + limit, beanColorParsingRowMapper);
    }

    @Override
    public Set<Color> getColorByPhoneId(long phoneId) {
        String sql = "select colorId from phone2color where phoneId = ?";
        List<Long> keys = jdbcTemplate.queryForList(sql, new Object[] { phoneId }, Long.class);
        return keys.stream().map(jdbcColorDao::get).map(Optional::get).collect(Collectors.toSet());
    }

    @Override
    public int getPhoneNumberContainsOnStock() {
        String sql = "select Count(phones.*) from (phones inner join stocks on id = phoneId) where (stock > 0 and price is not null"+consructSearch()+")";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Optional<Stock> getStockByPhoneId(long phoneId) {
        String sql = "select * from stocks where phoneId = ?";
        Stock stock = jdbcTemplate.queryForObject(sql, new Object[] { phoneId }, new BeanPropertyRowMapper<>(Stock.class));
        return Optional.of(stock);
    }

    private String consructSearch(){
        if (search == null || search.isEmpty()){
            return "";
        }
        return " and (brand='"+search+"' or model='"+search+"')";
    }

    @Override
    public void setSearch(String search) {
        if (search != null){
            this.search = search;
        }
    }

    @Override
    public String getSearch() {
        if (search == null){
            return "";
        }
        return search;
    }

    @Override
    public List<Phone> getPhoneListById(Set<Long> setId) {
        if (setId == null || setId.isEmpty()){
            return new ArrayList<>();
        }
        List<Phone> phones = new ArrayList<>();
        for (Long id: setId){
           Optional<Phone> phone = get(id);
           if(phone.isPresent()){
               phones.add(phone.get());
           }
        }
        return phones;
    }

    @Override
    public void removeFromStock(Long phoneId, Long quantity) {
        String query = "update stocks set stock=? where phoneId = ?";
        int common = getStockByPhoneId(phoneId).get().getStock();
        jdbcTemplate.update(query, common - quantity, phoneId);
    }
}
