package com.expocalendar.project.web.listeners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerImpl implements HttpSessionListener {
    private final static Logger LOGGER = Logger.getLogger(SessionListenerImpl.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        LOGGER.info("New Session at " + session.getCreationTime() + " with ID " +
                session.getId() + " was created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        LOGGER.info("Session with " + session.getId() + " ID was destroyed");
    }

}
