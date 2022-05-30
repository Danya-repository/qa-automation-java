package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.ifaces.MessageService;
import com.tcs.edu.printer.DecoratingMessageService;

import static com.tcs.edu.constants.Severity.*;

class Application {
    public static void main(String[] args) {

        Message m1 = new Message(REGULAR, "Hello world 1");
        Message m2 = new Message(REGULAR, "Hello world 2");
        Message m3 = new Message(REGULAR, "Hello world 3");
        Message m4 = new Message(MINOR, "Hello world 4");
        Message m5 = new Message(MAJOR, "Hello world 5");

        MessageService service = new DecoratingMessageService();
        service.create(m1, m2, m3, m4, m5);

        System.out.println(service.findByPrimaryKey(m1.getId()));

        System.out.println(service.findAll());
    }
}