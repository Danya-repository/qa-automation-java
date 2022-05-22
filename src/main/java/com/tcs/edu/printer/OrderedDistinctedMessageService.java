package com.tcs.edu.printer;

import com.tcs.edu.constants.Doubling;
import com.tcs.edu.constants.MessageOrder;
import com.tcs.edu.domain.Message;
import com.tcs.edu.decorator.*;
import com.tcs.edu.exceptions.LogException;
import com.tcs.edu.ifaces.MessageDecorator;
import com.tcs.edu.ifaces.MessageService;
import com.tcs.edu.ifaces.Printer;

import static com.tcs.edu.constants.Doubling.*;
import static com.tcs.edu.constants.MessageOrder.*;
import static com.tcs.edu.utils.Helpers.*;

public class OrderedDistinctedMessageService extends ValidatedService implements MessageService {

    private MessageDecorator timeDecorator;
    private Printer consolePrinter;

    public OrderedDistinctedMessageService() {
        this.timeDecorator = new TimestampMessageDecorator();
        this.consolePrinter = new ConsolePrinter();
    }

    public OrderedDistinctedMessageService(MessageDecorator timeDecorator, Printer printer) {
        this.timeDecorator = timeDecorator;
        this.consolePrinter = printer;
    }

    @Override
    public void processMessage(Message message, Message... messages) {
        try {
            super.isArgsValid(message, messages);
        } catch (IllegalArgumentException e) {
            throw new LogException(message, e);
        }

        Message[] processArr;
        processArr = getArrayAfterPreprocessing(message, messages);

        for (Message messageItem: processArr) {
            consolePrinter.print(timeDecorator.decorate(SeverityDecorator.decorate(messageItem.getBody(),
                    messageItem.getSeverityLevel())));
        }
    }

    @Override
    public void processMessage(MessageOrder order, Message message, Message... messages) {
        try {
            super.isArgsValid(order);
        } catch (IllegalArgumentException e) {
            throw new LogException(order, e);
        }

        if (order == ASC) {
            processMessage(message, messages);
        } else if (order == DESC) {
            processMessage(null, getReverseArr(getArrayAfterPreprocessing(message, messages)));
        }
    }

    @Override
    public void processMessage(MessageOrder order,
                               Doubling doubling, Message message, Message... messages) {
        try {
            super.isArgsValid(doubling);
        } catch (IllegalArgumentException e) {
            throw new LogException(doubling, e);
        }

        if (doubling == DOUBLES) {
            processMessage(order, message, messages);
        } else if (doubling == DISTINCT) {
            processMessage(order,
                            null,
                    getArrayWithoutDoubles(getArrayAfterPreprocessing(message, messages)));
        }
    }
}
