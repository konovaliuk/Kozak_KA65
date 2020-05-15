package com.expocalendar.project.service;

import com.expocalendar.project.entities.*;
import com.expocalendar.project.persistence.abstraction.interfaces.*;
import com.expocalendar.project.service.implementation.OrderServiceImpl;
import com.expocalendar.project.service.interfaces.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



public class OrderServiceImplTest {


    @Mock
    private ExpositionDAO expositionDAO;

    @Mock
    private ExpoHallDAO expoHallDAO;

    @Mock
    private CreditCardDAO creditCardDAO;

    @Mock
    private OrderDAO orderDAO;


    private OrderService orderService;


    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(expositionDAO, expoHallDAO, creditCardDAO, orderDAO);
    }


    @Test
    public void testProcessOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("number", "2");
        map.put("expoId", "1");

        Account account = Account.newBuilder().setLogin("John").
                setPassword("1212").setRole("user").setId(1).
                setFirstName("John").setLastName("Doe").setEmail("test@test").build();

        when(expoHallDAO.findExpoHall(anyInt())).thenReturn(new ExpoHall());
        when(expositionDAO.findExposition(anyInt())).thenReturn(new Exposition());
        when(creditCardDAO.findCard(anyInt())).thenReturn(null);

        boolean transaction = orderService.processOrder(account, map);

        verify(expoHallDAO).findExpoHall(anyInt());
        verify(expositionDAO).findExposition(anyInt());
        verify(creditCardDAO).findCard(anyInt());

        assertFalse(transaction);
    }

    @Test
    public void testGetOrders() {
        when(orderDAO.getOrders(anyInt())).thenReturn(new HashMap<>());
        Map<Order, Exposition> map = orderService.getOrders(new Account());
         verify(orderDAO).getOrders(anyInt());
        assertNotNull(map);
    }
}