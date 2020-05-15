package com.expocalendar.project.web.command;

import com.expocalendar.project.web.controller.ControllerHelper;
import org.mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.expocalendar.project.web.management.PagesManager.getProperty;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class CommandsTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    private ICommand command;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreationExecute() {
        command = new CreationCommand();
        when(request.getParameter("object")).thenReturn("test");
        String page = command.execute(request, response);
        verify(request).getParameter("object");
        assertEquals(getProperty("path.page.admin"), page);
    }


    @Test
    public void testDeleteExecute() {
        command = new DeleteCommand();
        when(request.getParameter("object")).thenReturn("test");
        String page = command.execute(request, response);
        verify(request).getParameter("object");
        assertEquals(getProperty("path.page.admin"), page);
    }

    @Test
    public void testLocalizationExecute() {
        command = new LocalizationCommand();
        when(request.getParameter("locale")).thenReturn("eng");
        when(request.getParameter("page")).thenReturn("page");
        when(request.getSession()).thenReturn(session);
        String page = command.execute(request, response);
        verify(session).setAttribute("locale", request.getParameter("locale"));
        verify(request).getParameter("page");
        assertEquals(request.getParameter("page"), page);


    }

    @Test
    public void testLogoutCommand() {
        command = new LogoutCommand();
        when(request.getSession()).thenReturn(session);
        String page = command.execute(request, response);
        verify(session).invalidate();
        assertEquals(getProperty("path.page.index"), page);
    }


    @Test
    public void testRedirectCommand() {
        command = new RedirectCommand();
        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/index");
        String url = command.execute(request, response);
        verify(request).getServletPath();
        verify(request).getSession();
        assertEquals(getProperty("path.page.index"), url);
    }


    @Test
    public void testShowCommand() {
        command = new ShowCommand();
        when(request.getParameter("object")).thenReturn("test");
        String page = command.execute(request, response);
        verify(request).getParameter("object");
        assertEquals(getProperty("path.page.admin"), page);
    }

    @Test

    public void testUpdateCommand(){
        command = new UpdateCommand();
        when(request.getParameter("object")).thenReturn("test");
        String page = command.execute(request, response);
        verify(request).getParameter("object");
        assertNull(page);

    }


}