package com.expocalendar.project.web.command;

import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.PagesManager;
import com.expocalendar.project.service.interfaces.AdminService;
import com.expocalendar.project.service.ServiceFactory;
import com.expocalendar.project.service.interfaces.SelectionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DeleteCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(DeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        AdminService adminService = serviceFactory.getAdminService();
        SelectionService selectionService = serviceFactory.getSelectionService();

        ControllerHelper controllerHelper = ControllerHelper.getInstance();

        Map<String, String> requestParameters;

        String obj = request.getParameter("object");

        switch (obj) {
            case "exposition":
                requestParameters = controllerHelper.extractParameters(request);
                adminService.deleteExposition(Integer.valueOf(requestParameters.get("expositionId")));
                request.getSession().setAttribute("allExpositions", selectionService.getAllExpositions());
                request.getSession().removeAttribute("updExposition");
                break;

            case "hall":
                requestParameters = controllerHelper.extractParameters(request);
                adminService.deleteExpoHall(Integer.valueOf(requestParameters.get("hallId")));
                request.getSession().setAttribute("halls", selectionService.getExpoHalls());
                request.getSession().removeAttribute("updHall");
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return PagesManager.getProperty("path.page.admin");
    }
}
