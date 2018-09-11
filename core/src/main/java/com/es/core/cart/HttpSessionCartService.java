package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Service
public class HttpSessionCartService implements CartService {

    private PhoneDao phoneDao;
    @Resource
    private Cart cart;

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        Map<Long, Long> phones = cart.getPhones();
        Long currentQuantity = phones.get(phoneId);
        if(currentQuantity == null){
            phones.put(phoneId, quantity);
        }else{
            phones.put(phoneId, quantity + currentQuantity);
        }
        cart.setPhones(phones);
        reCalcCart();
    }

    @Override
    public void update(Map<Long, Long> items) {
        removeAll();
        for(Map.Entry<Long, Long> entry: items.entrySet()){
          this.addPhone(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void remove(Long phoneId) {
        Map<Long, Long> phones = cart.getPhones();
        if (!phones.containsKey(phoneId)){
            return;
        }
        phones.remove(phoneId);
        cart.setPhones(phones);
        reCalcCart();
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
        Map<Long, Long> phones = cart.getPhones();
        for(Iterator<Map.Entry<Long, Long>> it = phones.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Long, Long> e = it.next();
            int commonQuantity = phoneDao.getStockByPhoneId(e.getKey()).get().getStock();
            if (e.getValue() > commonQuantity){
                flag = false;
                it.remove();
            }
        }
        cart.setPhones(phones);
        return flag;
    }

    @Override
    public void removeAll() {
        cart.setQuantity(0);
        cart.setSumma(new BigDecimal(0));
        cart.setPhones(new HashMap<>());
    }

    private void reCalcCart(){
        Map<Long, Long> phones = cart.getPhones();
        Long q = 0L;
        BigDecimal summa = new BigDecimal(0);
        for(Map.Entry<Long, Long> e: phones.entrySet()){
            if(checkPricePresent(e.getKey())) {
                q += e.getValue();
                summa = summa.add(getPrice(e.getKey()).multiply(new BigDecimal(e.getValue())));
            }
        }
        cart.setSumma(summa);
        cart.setQuantity(q);
    }

    private boolean checkPricePresent(Long phoneId){
        BigDecimal price = getPrice(phoneId);
        return price != null;
    }
}
