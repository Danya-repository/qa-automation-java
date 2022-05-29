package com.tcs.edu.printer;

import com.tcs.edu.decorator.*;

import static com.tcs.edu.decorator.Doubling.*;
import static com.tcs.edu.decorator.MessageOrder.*;
import static com.tcs.edu.utils.Helpers.*;

public class MessageService {

    public static void processMessage(Severity severity, String message, String... messages) {
        if (severity == null) { return; }

        String[] processArr;
        processArr = getArrayAfterPreprocessing(message, messages);
        ConsolePrinter.print(TimestampMessageDecorator.decorate(SeverityDecorator.decorate(severity,message)));

        for (String messageItem: processArr) {
            ConsolePrinter.print(TimestampMessageDecorator.decorate(SeverityDecorator.decorate(severity,messageItem)));
        }
    }

    public static void processMessage(Severity severity, MessageOrder order, String message, String... messages) {
        if (severity == null || order == null) { return; }

        if (order == ASC) {
            processMessage(severity, message, messages);
        } else if (order == DESC) {
            processMessage(severity, null, getReverseArr(getArrayAfterPreprocessing(message, messages)));
        }
    }

    public static void processMessage(Severity severity, MessageOrder order,
                                      Doubling doubling, String message, String... messages) {

        if (severity == null || order == null || doubling == null) { return; }

        if (doubling == DOUBLES) {
            processMessage(severity, order, message, messages);
        } else if (doubling == DISTINCT) {
            processMessage(severity,
                            order,
                    null,
                            getArrayWithoutDoubles(getArrayAfterPreprocessing(message, messages)));
        }
    }
}
