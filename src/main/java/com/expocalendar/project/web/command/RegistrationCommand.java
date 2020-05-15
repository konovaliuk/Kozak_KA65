package com.expocalendar.project.web.command;

import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.exceptions.PasswordException;
import com.expocalendar.project.web.exceptions.RegistrationException;
import com.expocalendar.project.web.management.PagesManager;
import com.expocalendar.project.service.interfaces.AuthorizationService;
import com.expocalendar.project.service.ServiceFactory;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

public class RegistrationCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AuthorizationService authorizationService = ServiceFactory.getInstance().getAuthorizationService();
        Map<String, String> requestParameters = ControllerHelper.getInstance().extractParameters(request);
        HttpSession session = request.getSession();

        try {

            authorizationService.checkAccount(requestParameters);
            authorizationService.createAccount(requestParameters);
            request.setAttribute("regSuccess", new Object());

        } catch (PasswordException regException) {
            request.setAttribute("notEqualPasswords", new Object());

        } catch (RegistrationException e) {
            request.setAttribute("loginExists", new Object());

        } finally {
            setAttributes(requestParameters, session);
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");
        return PagesManager.getProperty("path.page.login");
    }

    private void setAttributes(Map<String, String> requestParameters, HttpSession session) {
        session.setAttribute("firstName", requestParameters.get("firstName"));
        session.setAttribute("lastName", requestParameters.get("lastName"));
        session.setAttribute("login", requestParameters.get("login"));
        session.setAttribute("email", requestParameters.get("email"));

    }


}
