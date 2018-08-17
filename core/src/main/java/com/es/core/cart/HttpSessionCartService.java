package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
public class HttpSessionCartService implements CartService {
    @Resource
    PhoneDao phoneDao;

    @Resource
    Cart cart;

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        Optional<Phone> optionalPhone = phoneDao.get(phoneId);
        if (!optionalPhone.isPresent()){
            return;
        }
        BigDecimal price = optionalPhone.get().getPrice();
        if (price == null){
            return;
        }
        cart.addPhone(phoneId, quantity, price);
    }

    @Override
    public void update(Map<Long, Long> items) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void remove(Long phoneId) {
        throw new UnsupportedOperationException("TODO");
    }
}
