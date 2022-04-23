package com.tcs.edu.printer;

import com.tcs.edu.decorator.*;

import static com.tcs.edu.decorator.Doubling.*;
import static com.tcs.edu.decorator.MessageOrder.*;
import static com.tcs.edu.utils.Helpers.*;

public class MessageService {

    private static String[] processArr = null;

    public static void processMessage(Severity severity, String message, String... messages) {

        if (severity == null || message == null) { return; }

        if (processArr == null) {
            processArr = getArrayAfterPreprocessing(message, messages);
        }

        for (String messageItem: processArr) {
            ConsolePrinter.print(TimestampMessageDecorator.decorate(SeverityDecorator.decorate(severity,messageItem)));
        }
        ;
        processArr = null;
    }

    public static void processMessage(Severity severity, MessageOrder order, String message, String... messages) {

        if (severity == null || message == null || order == null) { return; }

        if (order == ASC) {
            processMessage(severity, message, messages);
        } else if (order == DESC) {
            processArr = getReverseArr(getArrayAfterPreprocessing(message, messages));

            processMessage(severity, message, messages);
        }
    }

    public static void processMessage(Severity severity, MessageOrder order,
                                      Doubling doubling, String message, String... messages) {

        if (severity == null || message == null || order == null || doubling == null) { return; }

        if (doubling == DOUBLES) {
            processMessage(severity, order, message, messages);
        } else if (doubling == DISTINCT) {
            if (order == ASC) {
                processArr = getArrayWithoutDoubles(getArrayAfterPreprocessing(message, messages));
                processMessage(severity, order, message, messages);
            } else if (order == DESC) {
                processArr = getArrayWithoutDoubles(getReverseArr(getArrayAfterPreprocessing(message, messages)));
                processMessage(severity, message, messages);
            }
        }
    }
}
