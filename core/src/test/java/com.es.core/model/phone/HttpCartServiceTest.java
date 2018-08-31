package com.es.core.model.phone;

import com.es.core.cart.Cart;
import com.es.core.cart.HttpSessionCartService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HttpCartServiceTest {
    @InjectMocks
    private HttpSessionCartService httpSessionCartService = new HttpSessionCartService();
    @Mock
    PhoneDao phoneDao;
    @Spy
    private Cart cart = new Cart();

    @Before
    public void init(){
        initPhones();
    }

    @Test
    public void emptyCart(){
        Assert.assertEquals(new BigDecimal(0), httpSessionCartService.getCart().getSumma());
    }

    @Test
    public void notPresentPrice() throws NoSuchFieldException {
        httpSessionCartService.addPhone(4L, 4L);
        Assert.assertEquals(new BigDecimal(0), cart.getSumma());
    }

    @Test
    public void testAddPhone1Phone(){
        httpSessionCartService.addPhone(1L, 4L);
        Assert.assertEquals(4, cart.getQuantity());
    }

    @Test
    public void setPhone(){
        httpSessionCartService.addPhone(2L, 1L);
        httpSessionCartService.addPhone(1L, 1L);
        httpSessionCartService.addPhone(2L, 3L);
        httpSessionCartService.addPhone(3L, 1L);
        Assert.assertEquals(new BigDecimal(15), cart.getSumma());
    }

    @Test
    public void deletePhone(){
        httpSessionCartService.addPhone(2L, 1L);
        httpSessionCartService.addPhone(1L, 1L);
        httpSessionCartService.addPhone(2L, 3L);
        httpSessionCartService.addPhone(3L, 1L);
        httpSessionCartService.remove(2L);
        Assert.assertEquals(new BigDecimal(7), cart.getSumma());
    }

    @Test
    public void deleteAll(){
        httpSessionCartService.addPhone(2L, 1L);
        httpSessionCartService.addPhone(1L, 1L);
        httpSessionCartService.remove(2L);
        httpSessionCartService.remove(1L);
        Assert.assertEquals(new BigDecimal(0), cart.getSumma());
    }

    @Test
    public void updatePhone(){
        httpSessionCartService.addPhone(2L, 1L);
        httpSessionCartService.addPhone(1L, 1L);
        httpSessionCartService.addPhone(2L, 3L);
        httpSessionCartService.addPhone(3L, 1L);
        Map<Long, Long> update = new HashMap<>();
        update.put(1L, 3L);
        update.put(2L, 1L);
        httpSessionCartService.update(update);
        Assert.assertEquals(new BigDecimal(5), cart.getSumma());
    }

    public void initPhones(){
        Phone phone1 = mock(Phone.class);
        when(phone1.getPrice()).thenReturn(new BigDecimal(1));
        when(phone1.getId()).thenReturn(1L);
        when(phoneDao.get(1L)).thenReturn(Optional.of(phone1));

        Phone phone2 = mock(Phone.class);
        when(phone2.getId()).thenReturn(2L);
        when(phone2.getPrice()).thenReturn(new BigDecimal(2));
        when(phoneDao.get(2L)).thenReturn(Optional.of(phone2));

        Phone phone3 = mock(Phone.class);
        when(phone3.getId()).thenReturn(3L);
        when(phone3.getPrice()).thenReturn(new BigDecimal(6));
        when(phoneDao.get(3L)).thenReturn(Optional.of(phone3));

        Phone phone4 = mock(Phone.class);
        when(phone4.getId()).thenReturn(4L);
        when(phone4.getPrice()).thenReturn(null);
        when(phoneDao.get(4L)).thenReturn(Optional.of(phone4));
    }
}
