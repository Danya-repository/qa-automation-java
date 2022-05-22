package com.tcs.edu.printer;

import com.tcs.edu.constants.Doubling;
import com.tcs.edu.constants.MessageOrder;
import com.tcs.edu.domain.Message;

import static com.tcs.edu.utils.Helpers.getArrayAfterPreprocessing;

public abstract class ValidatedService {

    public boolean isArgsValid(Message message, Message... messages) {
        if (message == null && messages == null) {
            throw new IllegalArgumentException("Параметры message и messages равен null.");
        }
        var arr = getArrayAfterPreprocessing(message, messages);
        for (Message messageItem: arr) {
            if (messageItem.getBody() == null) {
                throw new IllegalArgumentException("Поле body равно null.");
            }
            if (messageItem.getBody().equals("")) {
                throw new IllegalArgumentException("Поле body равно \"\".");
            }
        }
        return true;
    }

    public boolean isArgsValid(MessageOrder order) {
        if (order == null) { throw new IllegalArgumentException("Параметр order равен null."); }
        return true;
    }

    public boolean isArgsValid(Doubling doubling) {
        if (doubling == null) { throw new IllegalArgumentException("Параметр doubling равен null."); }
        return true;
    }
}
