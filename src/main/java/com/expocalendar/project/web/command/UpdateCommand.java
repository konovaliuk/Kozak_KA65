package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.PagesManager;
import com.expocalendar.project.service.interfaces.AdminService;
import com.expocalendar.project.service.ServiceFactory;
import com.expocalendar.project.service.interfaces.SelectionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.util.Map;

public class UpdateCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(UpdateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        AdminService adminService = serviceFactory.getAdminService();
        SelectionService selectionService = serviceFactory.getSelectionService();

        ControllerHelper controllerHelper = ControllerHelper.getInstance();

        Map<String, String> requestParameters;
        HttpSession session = request.getSession();

        String obj = request.getParameter("object");
        String page = null;

        switch (obj) {
            case "exposition":
                try {
                    requestParameters = controllerHelper.extractParameters(request);
                    adminService.updateExposition(requestParameters);
                    session.setAttribute("allExpositions", selectionService.getAllExpositions());
                    session.removeAttribute("updExposition");
                    request.setAttribute("expositionUpdated", new Object());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                page = PagesManager.getProperty("path.page.admin");
                break;
            case "hall":
                requestParameters = controllerHelper.extractParameters(request);
                adminService.updateExpoHall(requestParameters);
                request.getSession().setAttribute("halls", selectionService.getExpoHalls());
                request.getSession().removeAttribute("updHall");
                page = PagesManager.getProperty("path.page.admin");
                request.setAttribute("hallUpdated", new Object());
                break;
            case "account":
                requestParameters = controllerHelper.extractParameters(request);
                Account updAccount = (Account) session.getAttribute("account");
                Account account = serviceFactory.getAuthorizationService().updateAccount(requestParameters, updAccount);
                session.setAttribute("account", account);
                page = PagesManager.getProperty("path.page.account");
                request.setAttribute("accountUpdated", new Object());
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return page;
    }
}
