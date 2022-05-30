package com.tcs.edu.ifaces;

import com.tcs.edu.constants.Severity;

public interface MessageDecorator {
    String decorate(String body);

    String decorate(String body, Severity severity);
}
