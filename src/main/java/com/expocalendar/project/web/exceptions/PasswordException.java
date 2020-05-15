package com.expocalendar.project.web.exceptions;

public class PasswordException extends RegistrationException {
    public PasswordException() {
    }

    public PasswordException(String message, Throwable exception) {
        super(message, exception);
    }

    public PasswordException(String message) {
        super(message);
    }

    public PasswordException(Throwable exception) {
        super(exception);
    }
}
