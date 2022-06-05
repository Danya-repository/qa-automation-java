package com.tcs.edu.exceptions;

import com.tcs.edu.constants.Doubling;
import com.tcs.edu.constants.MessageOrder;
import com.tcs.edu.domain.Message;

public class LogException extends RuntimeException {
    public LogException() {
        super();
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogException(Throwable cause) {
        super(cause);
    }

    protected LogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LogException(MessageOrder order, IllegalArgumentException e) {
        super("Error argument order. " + e.getMessage(), e.getCause(), false, true);
    }

    public LogException(Doubling doubling, IllegalArgumentException e) {
        super("Error argument doubling. " + e.getMessage(), e.getCause(), false, true);
    }

    public LogException(Message message, IllegalArgumentException e) {
        super("Error argument message. " + e.getMessage(), e.getCause(), false, true);
    }
}
