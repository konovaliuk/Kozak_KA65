package com.expocalendar.project.web.listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListenerImpl implements ServletRequestListener {
    private final static Logger LOGGER = Logger.getLogger(RequestListenerImpl.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest request = servletRequestEvent.getServletRequest();
        LOGGER.info("Request destroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest request = servletRequestEvent.getServletRequest();
        LOGGER.info("Request initialized");
    }
}
