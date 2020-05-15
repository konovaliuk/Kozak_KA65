package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.web.management.PagesManager;
import com.expocalendar.project.service.ServiceFactory;
import com.expocalendar.project.service.interfaces.OrderService;
import com.expocalendar.project.service.interfaces.SelectionService;
import org.apache.log4j.Logger;
import static com.expocalendar.project.web.management.PagesManager.getProperty;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RedirectCommand implements ICommand {
    private static final String LOGIN = "/login";
    private static final String REGISTRATION = "/registration";
    private static final String ORDER = "/order";
    private static final String MAIN = "/main";
    private static final String INDEX = "/index";
    private static final String ADMIN = "/admin";
    private static final String ACCOUNT = "/account";

    private final static Logger LOGGER = Logger.getLogger(RedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SelectionService selectionService = serviceFactory.getSelectionService();
        OrderService orderService = serviceFactory.getOrderService();

        HttpSession session = request.getSession();

        String userPath = request.getServletPath();
        String url = PagesManager.getProperty("path.page.error");

        switch (userPath) {
            case LOGIN:
                url = getProperty("path.page.login");
                break;
            case REGISTRATION:
                url = getProperty("path.page.registration");
                break;
            case ORDER:
                session.setAttribute("exposition", selectionService.
                        getExposition(Integer.valueOf(request.getParameter("expositionId"))));
                url = getProperty("path.page.order");
                break;
            case MAIN:
                url = getProperty("path.page.main");
                break;
            case INDEX:
                url = getProperty("path.page.index");
                break;
            case ADMIN:
                session.setAttribute("allExpositions", selectionService.getAllExpositions());
                session.setAttribute("halls", selectionService.getExpoHalls());
                url = getProperty("path.page.admin");
                break;
            case ACCOUNT:
                Account account = (Account) session.getAttribute("account");
                session.setAttribute("orders", orderService.getOrders(account));
                url = getProperty("path.page.account");
                break;
        }

        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return url;
    }
}
