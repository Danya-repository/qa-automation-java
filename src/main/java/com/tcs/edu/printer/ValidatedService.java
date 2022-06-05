package com.tcs.edu.printer;

import com.tcs.edu.constants.Doubling;
import com.tcs.edu.constants.MessageOrder;
import com.tcs.edu.domain.Message;

import static com.tcs.edu.utils.Helpers.getArrayAfterPreprocessing;

public abstract class ValidatedService {

    public boolean isArgsValid(Message message, Message... messages) {
        if (message == null) {
            throw new IllegalArgumentException("Parameters message is null.");
        }
        var arr = getArrayAfterPreprocessing(message, messages);
        for (Message messageItem: arr) {
            if (messageItem.getBody() == null) {
                throw new IllegalArgumentException("Field body is null.");
            }
            if (messageItem.getBody().equals("")) {
                throw new IllegalArgumentException("Field body is empty.");
            }
        }
        return true;
    }

    public boolean isArgsValid(MessageOrder order) {
        if (order == null) { throw new IllegalArgumentException("Parameter order is null."); }
        return true;
    }

    public boolean isArgsValid(Doubling doubling) {
        if (doubling == null) { throw new IllegalArgumentException("Parameter doubling is null."); }
        return true;
    }
}
