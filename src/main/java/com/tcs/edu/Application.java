package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.ifaces.MessageService;
import com.tcs.edu.printer.DecoratingMessageService;

import static com.tcs.edu.constants.Severity.*;

class Application {
    public static void main(String[] args) {

        Message m1 = new Message(REGULAR, "Hello world 1");

        MessageService service = new DecoratingMessageService();
        service.create(null, null);

        System.out.println(service.findAll());
    }
}