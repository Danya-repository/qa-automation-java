package com.tcs.edu;

import com.tcs.edu.constants.Doubling;
import com.tcs.edu.constants.MessageOrder;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.printer.OrderedDistinctedMessageService;

import java.util.HashSet;

import static com.tcs.edu.constants.Doubling.*;
import static com.tcs.edu.constants.MessageOrder.*;
import static com.tcs.edu.constants.Severity.*;

class Application {
    public static void main(String[] args) {

        Message m1 = new Message(REGULAR, "Hello world 1");
        Message m2 = new Message(REGULAR, "Hello world 2");
        Message m3 = new Message(REGULAR, "Hello world 3");
        Message m4 = new Message(MINOR, "Hello world 4");
        Message m5 = new Message(MINOR, "Hello world 5");
        Message m6 = new Message(MAJOR, "Hello world 6");
        Message m7 = new Message(REGULAR, "Hello world 1");
        Message m8 = new Message(REGULAR, "Hello world 1");
        Message m9 = new Message(REGULAR, "Hello world 1");

        // 9 - 1

        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter())
                .processMessage(null, null);

        MessageOrder order = null;
        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(order,
                m1, m2, m3, m4, m5, m6, m7, m8, m9);

        Doubling doubling = null;
        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(order,
                doubling,  m2, m3, m4, m5, m6, m7, m8, m9);

        // 9 - 2

        System.out.println(m1);

        System.out.println(m1.equals(m2));
        System.out.println(m7.equals(m9));

        HashSet<Message> h = new HashSet<Message>();
        h.add(m1);

        System.out.println(m1);



//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter())
//                .processMessage(m1, m2, m3, m4, m5, m6, m7, m8, m9);
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(ASC,
//                m2, m3, m4, m5, m6, m7, m8, m9);
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(DESC,
//                m2, m3, m4, m5, m6, m7, m8, m9);
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(ASC,
//                DOUBLES,  m2, m3, m4, m5, m6, m7, m8, m9);
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(DESC,
//                DOUBLES,  m2, m3, m4, m5, m6, m7, m8, m9);
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(ASC,
//                DISTINCT,  m2, m3, m4, m5, m6, m7, m8, m9);
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(DESC,
//                DISTINCT,  m2, m3, m4, m5, m6, m7, m8, m9);

    }
}