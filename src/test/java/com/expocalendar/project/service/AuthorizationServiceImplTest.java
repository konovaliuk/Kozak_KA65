package com.expocalendar.project.service;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.persistence.abstraction.interfaces.AccountDAO;;
import com.expocalendar.project.web.exceptions.RegistrationException;
import com.expocalendar.project.service.implementation.AuthorizationServiceImpl;
import com.expocalendar.project.service.interfaces.AuthorizationService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.*;


public class AuthorizationServiceImplTest {


    @Mock
    private AccountDAO accountDAO;

    private AuthorizationService authorizationService;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        authorizationService = new AuthorizationServiceImpl(accountDAO);
    }

    @Test
    public void checkAccount() throws RegistrationException {
        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put("login", "test");
        requestParameters.put("password", "test");
        requestParameters.put("repeat", "test");

        when(accountDAO.isExist(anyString())).thenReturn(false);
        authorizationService.checkAccount(requestParameters);
        verify(accountDAO).isExist(anyString());
    }

    @Test
    public void testCreateAccount() {
        Map<String, String> map = new HashMap<>();
        map.put("firstName", "John");
        map.put("lastName", "Doe");
        map.put("login", "test");
        map.put("password", "test");
        map.put("repeat", "test");
        map.put("email", "test@test");
        map.put("cardNumber", "4444555577778888");
        map.put("cvv", "333");
        map.put("cardHolder", "John Doe");
        map.put("month", "12");
        map.put("year", "20");

        when(accountDAO.createAccount(anyObject(), anyObject())).thenReturn(true);
        boolean created = authorizationService.createAccount(map);
        verify(accountDAO).createAccount(anyObject(), anyObject());
        assertTrue(created);

    }

    @Test
    public void updateAccount() {
        Map<String, String> map = new HashMap<>();
        map.put("accountId", "1");
        map.put("firstName", "John");
        map.put("lastName", "Doe");
        map.put("email", "test@test");

        Account account = Account.newBuilder().setLogin("John").
                setPassword("1212").setRole("user").setId(1).
                setFirstName("John").setLastName("Doe").setEmail("test@test").build();

        when(accountDAO.updateAccount(anyObject())).thenReturn(true);
        Account updated = authorizationService.updateAccount(map, account);
        verify(accountDAO).updateAccount(anyObject());
        assertEquals(account, updated);

    }

    @Test
    public void findAccount() {
        Account account = new Account();
        account.setLogin("John");
        account.setPassword("1212");

        when(accountDAO.findAccount(anyString(),  anyString())).thenReturn(account);
        Account found = authorizationService.findAccount("John", "1212");
        verify(accountDAO).findAccount(anyString(), anyString());
        assertNotNull(found);
    }
}