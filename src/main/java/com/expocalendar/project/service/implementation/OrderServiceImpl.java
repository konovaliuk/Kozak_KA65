package com.expocalendar.project.service.implementation;

import com.expocalendar.project.entities.*;
import com.expocalendar.project.persistence.abstraction.interfaces.*;
import com.expocalendar.project.web.management.MessageManager;
import com.expocalendar.project.service.Validator;
import com.expocalendar.project.service.interfaces.OrderService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.*;

import java.text.SimpleDateFormat;
import java.util.*;


public class OrderServiceImpl implements OrderService {
    private ExpositionDAO expositionDAO;
    private ExpoHallDAO expoHallDAO;
    private CreditCardDAO creditCardDAO;
    private OrderDAO orderDAO;


    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(ExpositionDAO expositionDAO, ExpoHallDAO expoHallDAO,
                            CreditCardDAO creditCardDAO, OrderDAO orderDAO) {

        this.expositionDAO = expositionDAO;
        this.expoHallDAO = expoHallDAO;
        this.creditCardDAO = creditCardDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public boolean processOrder(Account account, Map<String, String> requestParameters) {

        boolean transaction = false;

        int ticketsNumber = Integer.valueOf(requestParameters.get("number"));
        int expoId = Integer.valueOf(requestParameters.get("expoId"));

        Exposition exposition = expositionDAO.findExposition(expoId);
        ExpoHall expoHall = expoHallDAO.findExpoHall(exposition.getExpoHallId());
        CreditCard creditCard = creditCardDAO.findCard(account.getId());

        double withdraw = exposition.getTicketPrice() * ticketsNumber;

        if (creditCard != null && Validator.validCard(requestParameters, creditCard, withdraw)) {
            String orderKey = generateKey(account);
            double remainder = creditCard.getBalance() - withdraw;
            Order order = new Order(orderKey, ticketsNumber, exposition.getDateTo());
            orderDAO.saveOrder(order, exposition, account.getId(), remainder);
            sendMail(order, account, exposition, expoHall);
            transaction = true;
            LOGGER.info(account.getLogin() + " made new order");
        }

        return transaction;
    }

    @Override
    public Map<Order, Exposition> getOrders(Account account) {
        return orderDAO.getOrders(account.getId());
    }

    private String generateKey(Account account) {
        return new SimpleDateFormat("yyMMddhhmmssMs").format(new Date()) + account.getId();
    }

    public void sendMail(Order order, Account account, Exposition exposition, ExpoHall expoHall) {

        String msg = formMessage(order, account, exposition, expoHall);

        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("expocalendar2020@gmail.com", "98kozak"));
        email.setSSLOnConnect(true);

        try {
            email.setFrom("expocalendar2020@gmail.com");
            email.setSubject("Tickets Order");
            email.setMsg(msg);
            email.addTo(account.getEmail());
            email.send();

        } catch (EmailException e) {
            LOGGER.error("EmailException occurred in " + this.getClass().getSimpleName(), e);
        }

    }

    private static String formMessage(Order order, Account account, Exposition exposition, ExpoHall expoHall) {
        return String.format(MessageManager.getProperty("message.mail.content"),
                account.getFirstName(), account.getLastName(), exposition.getTitle(),
                expoHall.getName(), exposition.getDateFrom(), exposition.getDateTo(),
                order.getOrderKey(), order.getTicketsNumber());
    }

}
