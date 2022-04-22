package com.tcs.edu;

import com.tcs.edu.printer.MessageService;

import static com.tcs.edu.decorator.Severity.*;
import static com.tcs.edu.decorator.MessageOrder.*;

class Application {
    public static void main(String[] args) {
        MessageService.processMessage(
                MAJOR,
                DESC,
                "Hello world 1",
                "Hello world 2", "Hello world 3", "Hello world 4",
                          "Hello world 5", "Hello world 6"
            );
    }
}