package com.expocalendar.project.web.exceptions;

public class RegistrationException extends Exception {
    public RegistrationException() {
    }

    public RegistrationException(String message, Throwable exception) {
        super(message, exception);
    }

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(Throwable exception) {
        super(exception);
    }
}
