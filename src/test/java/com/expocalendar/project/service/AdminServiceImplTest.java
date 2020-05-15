package com.expocalendar.project.service;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.service.implementation.AdminServiceImpl;
import com.expocalendar.project.service.interfaces.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class AdminServiceImplTest {

    @Mock
    private ExpositionDAO expositionDAO;

    @Mock
    private ExpoHallDAO expoHallDAO;


    private AdminService adminService;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        adminService = new AdminServiceImpl(expositionDAO, expoHallDAO);
    }

    @Test
    public void testCreateExposition() throws MalformedURLException {
        Map<String, String> map = new HashMap<>();
        map.put("title", "Test");
        map.put("dateFrom", "2020-05-01");
        map.put("dateTo", "2020-06-02");
        map.put("theme", "Theme");
        map.put("price", "100");
        map.put("hallId", "5");
        map.put("description", "Some desc");
        map.put("beginTime", "17:00:00");
        map.put("url", "http://hanassets.nd.gov/images/product/test.png");
        when(expositionDAO.createExposition(anyObject())).thenReturn(true);
        boolean created = adminService.createExposition(map);
        verify(expositionDAO).createExposition(anyObject());
        assertTrue(created);


    }

    @Test
    public void testUpdateExposition() throws MalformedURLException {
        Map<String, String> map = new HashMap<>();
        map.put("title", "Test");
        map.put("dateFrom", "2020-05-01");
        map.put("dateTo", "2020-05-02");
        map.put("theme", "Theme");
        map.put("price", "100");
        map.put("hallId", "5");
        map.put("description", "Some desc");
        map.put("beginTime", "17:00:00");
        map.put("url", "http://hanassets.nd.gov/images/product/test.png");
        map.put("expositionId", "1");
        when(expositionDAO.updateExposition(anyObject())).thenReturn(true);
        boolean updated = adminService.updateExposition(map);
        verify(expositionDAO).updateExposition(anyObject());
        assertTrue(updated);

    }

    @Test
    public void testDeleteExposition() {
        when(expositionDAO.deleteExposition(anyInt())).thenReturn(true);
        boolean deleted = adminService.deleteExposition(anyInt());
        verify(expositionDAO).deleteExposition(anyInt());
        assertTrue(deleted);
    }

    @Test
    public void testCreateExpoHall() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Test");
        map.put("address", "some address");
        map.put("hallId", "5");
        ExpoHall expoHall = new ExpoHall();
        when(expoHallDAO.createExpoHall(anyObject())).thenReturn(true);
        boolean deleted = adminService.createExpoHall(map);
        verify(expoHallDAO).createExpoHall(anyObject());
        assertTrue(deleted);

    }

    @Test
    public void testUpdateExpoHall() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Test");
        map.put("address", "some address");
        map.put("hallId", "5");
        when(expoHallDAO.updateExpoHall(anyObject())).thenReturn(true);
        boolean updated = adminService.updateExpoHall(map);
        verify(expoHallDAO).updateExpoHall(anyObject());
        assertTrue(updated);
    }

    @Test
    public void testDeleteExpoHall() {
        when(expoHallDAO.deleteExpoHall(anyInt())).thenReturn(true);
        boolean deleted = adminService.deleteExpoHall(anyInt());
        verify(expoHallDAO).deleteExpoHall(anyInt());
        assertTrue(deleted);
    }
}