package com.tcs.edu.domain;

import com.tcs.edu.constants.Severity;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Message{" +
                "severityLevel=" + severityLevel +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return severityLevel == message.severityLevel && Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(severityLevel, body);
    }

    public String getBody() {
        return body;
    }
}
