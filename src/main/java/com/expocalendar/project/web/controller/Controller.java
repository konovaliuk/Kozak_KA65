package com.expocalendar.project.web.controller;

import com.expocalendar.project.web.command.ICommand;
import com.expocalendar.project.web.command.LoginCommand;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info(this.getClass().getSimpleName() + " processing new request");
        String page;

        ControllerHelper controllerHelper = ControllerHelper.getInstance();

        ICommand command = controllerHelper.defineCommand(request.getParameter("command"));

        page = command.execute(request, response);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
