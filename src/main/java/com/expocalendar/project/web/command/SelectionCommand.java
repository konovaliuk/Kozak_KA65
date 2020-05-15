package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.PagesManager;
import com.expocalendar.project.service.ServiceFactory;
import com.expocalendar.project.service.Validator;
import com.expocalendar.project.service.interfaces.SelectionService;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class SelectionCommand implements ICommand {

    private SelectionService selectionService = ServiceFactory.getInstance().getSelectionService();

    private final static Logger LOGGER = Logger.getLogger(SelectionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> requestParameters = ControllerHelper.getInstance().extractParameters(request);

        Validator.validateSelectionParameters(requestParameters);

        setAttributes(request, requestParameters);

        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return PagesManager.getProperty("path.page.main");
    }

    private void setAttributes(HttpServletRequest request, Map<String, String> requestParameters) {

        int numberOfExpositions = selectionService.getNumberOfExpositions(requestParameters);
        int page = 1;
        int recordsPerPage = 9;
        int numberOfPages = (int) Math.ceil(numberOfExpositions * 1.0 / recordsPerPage);

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<ExpoHall> expoHalls = selectionService.getExpoHalls();
        List<String> themes = selectionService.findThemes();
        List<Exposition> expositions = selectionService.findExpositions(requestParameters,
                recordsPerPage, (page - 1) * recordsPerPage);


        HttpSession session = request.getSession();

        session.setAttribute("numberOfPages", numberOfPages);
        session.setAttribute("currentPage", page);
        session.setAttribute("dateFrom", requestParameters.get("dateFrom"));
        session.setAttribute("dateTo", requestParameters.get("dateTo"));
        session.setAttribute("theme", requestParameters.get("theme"));
        session.setAttribute("hallId", requestParameters.get("hallId"));
        session.setAttribute("halls", expoHalls);
        session.setAttribute("themes", themes);

        session.setAttribute("expositions", expositions);

        session.removeAttribute("nothingFound");

        if (expositions.isEmpty()) {
            session.setAttribute("nothingFound", new Object());
        }
    }

}
