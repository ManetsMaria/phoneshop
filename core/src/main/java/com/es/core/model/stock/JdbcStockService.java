package com.es.core.model.stock;

import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JdbcStockService implements StockService {
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private CartService cartService;

    @Override
    public boolean checkAdd(Long phoneId, Long quantity) {
        int commonQuantity = phoneDao.getStockByPhoneId(phoneId).get().getStock();
        Cart cart = cartService.getCart();
        Long currentQuantity = cart.getPhoneQuantity(phoneId);
        return currentQuantity + quantity <= commonQuantity;
    }

    @Override
    public boolean checkUpdate(Long phoneId, Long quantity) {
        int commonQuantity = phoneDao.getStockByPhoneId(phoneId).get().getStock();
        return quantity <= commonQuantity;
    }

    @Override
    public void removeFromStock(Long phoneId, Long quantity) {
        int common = phoneDao.getStockByPhoneId(phoneId).get().getStock();
        phoneDao.updateStock(phoneId, common - quantity);
    }
}
