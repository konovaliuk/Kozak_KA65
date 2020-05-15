package com.expocalendar.project.web.command;

import com.expocalendar.project.web.management.PagesManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        LOGGER.info(this.getClass().getSimpleName() + " executed");
        return PagesManager.getProperty("path.page.index");
    }
}
