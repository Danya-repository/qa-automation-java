package com.tcs.edu.printer;

import com.tcs.edu.constants.Doubling;
import com.tcs.edu.constants.MessageOrder;
import com.tcs.edu.domain.Message;

public abstract class ValidatedService {

    public boolean isArgsValid(Message message, Message... messages) {
        if (message == null && messages == null) { return false; }
        return true;
    }

    public boolean isArgsValid(MessageOrder order) {
        if (order == null) { return false; }
        return true;
    }

    public boolean isArgsValid(Doubling doubling) {
        if (doubling == null) { return false; }
        return true;
    }
}
