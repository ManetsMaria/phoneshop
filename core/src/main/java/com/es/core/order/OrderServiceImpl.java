package com.es.core.order;

import com.es.core.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    PhoneDao phoneDao;

    @Override
    public Order createOrder(Cart cart) {
        Order order = new Order();
        setDelivery(order);
        order.setSubtotal(cart.getSumma());
        BigDecimal total = order.getDeliveryPrice().add(order.getSubtotal());
        order.setTotalPrice(total);
        List<OrderItem> items = new ArrayList<>();
        for (Map.Entry <Long, Long> e: cart.getPhones().entrySet()){
            Phone phone = phoneDao.get(e.getKey()).get();
            OrderItem orderItem = new OrderItem();
            orderItem.setPhone(phone);
            orderItem.setQuantity(e.getValue());
            BigDecimal price = phone.getPrice().multiply(new BigDecimal(e.getValue()));
            orderItem.setSubtotal(price);
            items.add(orderItem);
        }
        order.setOrderItems(items);
        return order;
    }

    @Override
    public void placeOrder(Order order) throws OutOfStockException {
        checkStock(order);
        for(OrderItem orderItem: order.getOrderItems()){
            phoneDao.removeFromStock(orderItem.getPhone().getId(), orderItem.getQuantity());
        }
        order.setId(getOrderId());
    }

    private void setDelivery(Order order){
        Resource resource = new ClassPathResource("/order.properties");
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            order.setDeliveryPrice(new BigDecimal(props.getProperty("delivery.price")));
        } catch (IOException | NumberFormatException e) {
            order.setDeliveryPrice(new BigDecimal(0));
        }
    }

    private void checkStock(Order order) throws OutOfStockException {
        boolean flag = true;
        for(int i = 0; i < order.getOrderItems().size(); i++){
            OrderItem orderItem = order.getOrderItems().get(i);
            int commonQuantity = phoneDao.getStockByPhoneId(orderItem.getPhone().getId()).get().getStock();
            if (orderItem.getQuantity() > commonQuantity){
                flag = false;
                order.getOrderItems().remove(i);
                i--;
            }
        }
        if(!flag){
            throw new OutOfStockException();
        }
    }

    private long getOrderId(){
        return 2L;
    }
}
