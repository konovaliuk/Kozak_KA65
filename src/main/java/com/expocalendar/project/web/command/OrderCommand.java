package com.expocalendar.project.web.command;

import com.expocalendar.project.entities.Account;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.web.controller.ControllerHelper;
import com.expocalendar.project.web.management.PagesManager;
import com.expocalendar.project.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class OrderCommand implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(OrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> requestParameters = ControllerHelper.getInstance().extractParameters(request);

        Account account = (Account) request.getSession().getAttribute("account");

        boolean transaction = ServiceFactory.getInstance().getOrderService().processOrder(account, requestParameters);

        if (transaction) {
            Exposition exposition = (Exposition) request.getSession().getAttribute("exposition");
            double sum = Integer.valueOf(requestParameters.get("number")) * exposition.getTicketPrice();
            request.setAttribute("sum", sum);
            request.setAttribute("orderSuccess", new Object());
        } else {
            request.setAttribute("orderFail", new Object());
        }
        LOGGER.info(this.getClass().getSimpleName() + " executed");

        return PagesManager.getProperty("path.page.order");

    }
}
