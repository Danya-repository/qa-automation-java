package com.tcs.edu.printer;

import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.decorator.TimestampMessageDecorator;

import static com.tcs.edu.decorator.MessageOrder.*;

public class MessageService {

    public static void processMessage(Severity severity, String message, String... messages) {

        if (severity != null && message != null) {
            ConsolePrinter.print(TimestampMessageDecorator.decorate(SeverityDecorator.decorate(severity,message)));

            for (String messageItem: messages) {

                if (messageItem != null) {
                    ConsolePrinter.print(TimestampMessageDecorator.decorate(SeverityDecorator.decorate(severity,messageItem)));
                }
            }
        }
    }

    public static void processMessage(Severity severity, MessageOrder order, String message, String... messages) {

        if (severity == null || message == null) {return;}

        if (order == ASC) {
            ConsolePrinter.print(TimestampMessageDecorator.decorate(SeverityDecorator.decorate(severity,message)));

            for (int i = 0; i < messages.length; i++) {

                if (messages[i] != null) {
                    ConsolePrinter.print(
                            TimestampMessageDecorator.decorate(SeverityDecorator.decorate(severity,messages[i]))
                    );
                }
            }
        } else if (order == DESC) {
            for (int i = messages.length - 1; i >= 0; i--) {

                if (messages[i] != null) {
                    ConsolePrinter.print(
                            TimestampMessageDecorator.decorate(SeverityDecorator.decorate(severity,messages[i]))
                    );
                }
            }

            ConsolePrinter.print(TimestampMessageDecorator.decorate(SeverityDecorator.decorate(severity,message)));
        }
    }
}
