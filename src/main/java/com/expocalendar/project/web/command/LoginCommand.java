package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;

import com.expocalendar.project.web.management.PagesManager;
import com.expocalendar.project.service.ServiceFactory;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String page;

        Account account = ServiceFactory.getInstance().getAuthorizationService().
                findAccount(request.getParameter("login"), request.getParameter("password"));

        if (account != null) {
            page = PagesManager.getProperty("path.page.main");
            request.getSession().setAttribute("account", account);
        } else {
            page = PagesManager.getProperty("path.page.login");
            request.setAttribute("noSuchAccount", new Object());
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");
        return page;
    }
}
