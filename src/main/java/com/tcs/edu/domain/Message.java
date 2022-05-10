package com.tcs.edu.domain;

import com.tcs.edu.constants.Severity;

public class Message {

    private Severity severityLevel;
    private String body;

    public Message(Severity severity, String message) {
        this.severityLevel = severity;
        this.body = message;
    }

    public Severity getSeverityLevel() {
        return severityLevel;
    }

    public String getBody() {
        return body;
    }
}
