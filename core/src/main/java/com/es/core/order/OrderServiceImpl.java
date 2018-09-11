package com.es.core.order;

import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private DeliveryService deliveryService;
    @Resource
    private StockService stockService;
    @Resource
    private CartService cartService;

    private Map<Long, Order> orders = new HashMap<>();
    private long orderIndex = 1L;

    @Override
    public Order createOrder(Cart cart) {
        Order order = new Order();
        order.setDeliveryPrice(deliveryService.getDelivery());
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
            stockService.removeFromStock(orderItem.getPhone().getId(), orderItem.getQuantity());
        }
        order.setId(getOrderId());
        order.setStatus(OrderStatus.NEW);
        orders.put(order.getId(), order);
    }

    private void checkStock(Order order) throws OutOfStockException {
        boolean isRemove = false;
        Iterator<OrderItem> itr = order.getOrderItems().iterator();
        while(itr.hasNext()){
            OrderItem orderItem = itr.next();
            if(!stockService.checkUpdate(orderItem.getPhone().getId(), orderItem.getQuantity())){
               isRemove = true;
               itr.remove();
            }
        }
        if(isRemove){
            cartService.checkCart();
            throw new OutOfStockException();
        }
    }

    private long getOrderId(){
        return orderIndex++;
    }

    @Override
    public Map<Long, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<Long, Order> orders) {
        this.orders = orders;
    }

    @Override
    public Order getOrderById(long id){
        return orders.get(id);
    }
}
