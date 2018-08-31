package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
public class HttpSessionCartService implements CartService {

    private PhoneDao phoneDao;
    @Autowired
    private Cart cart;

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        BigDecimal price = getPrice(phoneId);
        if (price == null){
            return;
        }
        cart.addPhone(phoneId, quantity, price);
    }

    @Override
    public void update(Map<Long, Long> items) {
        cart.removeAll();
        for(Map.Entry<Long, Long> entry: items.entrySet()){
          addPhone(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void remove(Long phoneId) {
        BigDecimal price = getPrice(phoneId);
        /*if (price == null){
            return;
        }*/ //   !!!!
        cart.removePhone(phoneId, price);
    }

    private BigDecimal getPrice(long phoneId){
        Optional<Phone> optionalPhone = phoneDao.get(phoneId);
        if (!optionalPhone.isPresent()){
            return null;
        }
        return optionalPhone.get().getPrice();
    }

    @Resource
    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    public PhoneDao getPhoneDao() {
        return phoneDao;
    }

    @Override
    public boolean checkCart(){
        boolean flag = true;
        for(Map.Entry<Long, Long> e:cart.getPhones().entrySet()){
            int commonQuantity = phoneDao.getStockByPhoneId(e.getKey()).get().getStock();
            if (e.getValue() > commonQuantity){
                flag = false;
                cart.removePhone(e.getKey(), getPrice(e.getKey()));
            }
        }
        return flag;
    }

}
