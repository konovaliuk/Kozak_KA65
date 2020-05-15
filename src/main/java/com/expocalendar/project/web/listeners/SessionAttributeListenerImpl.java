package com.expocalendar.project.web.listeners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private final static Logger LOGGER = Logger.getLogger(SessionAttributeListenerImpl.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        LOGGER.info("Attribute " + httpSessionBindingEvent.getName() + " with value: "
                + httpSessionBindingEvent.getValue().toString() + " has been added");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        LOGGER.info("Attribute " + httpSessionBindingEvent.getName() + " with value: "
                + httpSessionBindingEvent.getValue().toString() + " has been removed");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        LOGGER.info("Attribute " + httpSessionBindingEvent.getName() + " with value: "
                + httpSessionBindingEvent.getValue().toString() + " has been replaced");
    }
}
