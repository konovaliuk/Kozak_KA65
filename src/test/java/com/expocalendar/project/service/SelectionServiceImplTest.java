package com.expocalendar.project.service;


import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.service.implementation.SelectionServiceImpl;
import com.expocalendar.project.service.interfaces.SelectionService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class SelectionServiceImplTest {

    @Mock
    private ExpositionDAO expositionDAO;

    @Mock
    private ExpoHallDAO expoHallDAO;

    @Mock
    private Exposition exposition;

    @Mock
    private ExpoHall expoHall;


    private SelectionService selectionService;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        selectionService = new SelectionServiceImpl(expositionDAO, expoHallDAO);
    }

    @Test
    public void testMockCreation() {
        assertNotNull(expositionDAO);
        assertNotNull(expoHallDAO);
        assertNotNull(exposition);
        assertNotNull(expoHall);
    }

    @Test
    public void testGetAllExpositions() {
        List<Exposition> expositionList = new ArrayList<>();
        expositionList.add(new Exposition());
        expositionList.add(new Exposition());
        expositionList.add(new Exposition());
        when(expositionDAO.findAll()).thenReturn(expositionList);
        List<Exposition> result = selectionService.getAllExpositions();
        verify(expositionDAO).findAll();
        assertEquals(expositionList, result);
    }

    @Test
    public void testGetExpoHalls() {
        List<ExpoHall> expoHalls = new ArrayList<>();
        expoHalls.add(new ExpoHall());
        expoHalls.add(new ExpoHall());
        expoHalls.add(new ExpoHall());
        when(expoHallDAO.findAll()).thenReturn(expoHalls);
        List<ExpoHall> result = selectionService.getExpoHalls();
        verify(expoHallDAO).findAll();
        assertEquals(expoHalls, result);
    }

    @Test
    public void testGetExpoHall() {
        List<ExpoHall> expoHalls = new ArrayList<>();
        expoHalls.add(new ExpoHall(1, "Test 1", "Test address 1"));
        expoHalls.add(new ExpoHall(2, "Test 2", "Test address 2"));
        expoHalls.add(new ExpoHall(3, "Test 3", "Test address 3"));
        when(expoHallDAO.findExpoHall(2)).thenReturn(expoHalls.get(1));
        ExpoHall result = selectionService.getExpoHall(2);
        verify(expoHallDAO).findExpoHall(2);
        assertEquals(new ExpoHall(2, "Test 2", "Test address 2"), result);
    }


    @Test
    public void testFindExpositions() {
        Map<String, String> map = new HashMap<>();
        Validator.validateSelectionParameters(map);
        List<Exposition> expositionList = new ArrayList<>();
        expositionList.add(new Exposition());
        expositionList.add(new Exposition());
        expositionList.add(new Exposition());
        when(expositionDAO.findExpositions(anyString())).thenReturn(expositionList);
        List<Exposition> result = selectionService.findExpositions(map, 10, 20);
        verify(expositionDAO).findExpositions(anyString());
        assertEquals(expositionList, result);

    }

    @Test
    public void testGetNumberOfExpositions() {
        Map<String, String> map = new HashMap<>();
        Validator.validateSelectionParameters(map);
        when(expositionDAO.countExpositions(anyString())).thenReturn(10);
        int count = selectionService.getNumberOfExpositions(map);
        verify(expositionDAO).countExpositions(anyString());
        assertEquals(10, count);
    }


    @Test
    public void testFindThemes() {
        List<String> themes = new ArrayList<>();
        when(expositionDAO.findThemes()).thenReturn(themes);
        List<String> result = selectionService.findThemes();
         verify(expositionDAO).findThemes();
        assertEquals(themes, result);
    }

    @Test
    public void testGetExposition() {
        List<Exposition> expositionList = new ArrayList<>();
        Exposition exp1 = new Exposition();
        exp1.setId(1);
        Exposition exp2 = new Exposition();
        exp1.setId(20);
        Exposition exp3 = new Exposition();
        exp1.setId(3);
        expositionList.add(exp1);
        expositionList.add(exp2);
        expositionList.add(exp3);
        when(expositionDAO.findExposition(20)).thenReturn(expositionList.get(1));
        Exposition result = selectionService.getExposition(20);
        verify(expositionDAO).findExposition(20);
        assertEquals(exp2, result);
    }

}




