package com.expocalendar.project.web.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocalizationCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(LocalizationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("locale");
        request.getSession().setAttribute("locale", locale);
        LOGGER.info(this.getClass().getSimpleName() + " executed");
        return request.getParameter("page");
    }
}
