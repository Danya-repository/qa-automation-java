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
        Message m4 = new Message(MINOR, "");
        Message m5 = new Message(MINOR, null);
        Message m6 = new Message(MAJOR, "Hello world 6");
        Message m7 = new Message(REGULAR, "");
        Message m8 = new Message(REGULAR, "Hello world 1");
        Message m9 = new Message(REGULAR, "Hello world 1");

        // 10

        // Позитивные кейсы

//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter())
//                .processMessage(m1, m2, m3);

//        MessageOrder order = null;
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(order,
//                m1, m2, m3);
//
//        MessageOrder order = ASC;
//        Doubling doubling = null;
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(order,
//                doubling, m1, m2, m3);

        // body: null

//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter())
//                .processMessage(m5, m6);
//
//        MessageOrder order = ASC;
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(order,
//                m5, m6);
//
//        MessageOrder order = ASC;
//        Doubling doubling = DISTINCT;
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(order,
//                doubling, m5, m6);

        // body: ""

//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter())
//                .processMessage(m7, m8);

//        MessageOrder order = ASC;
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(order,
//                m7, m8);
//
//        MessageOrder order = ASC;
//        Doubling doubling = DISTINCT;
//        new OrderedDistinctedMessageService(new TimestampMessageDecorator(), new ConsolePrinter()).processMessage(order,
//                doubling, m7, m8);

    }
}