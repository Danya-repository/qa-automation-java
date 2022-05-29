package com.tcs.edu.domain;

import com.tcs.edu.constants.Severity;

import java.util.Objects;
import java.util.UUID;

public class Message {

    private Severity severityLevel;
    private String body;
    private UUID id;

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
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return severityLevel == message.severityLevel && Objects.equals(body, message.body) && Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(severityLevel, body, id);
    }

    public UUID setId(UUID id) {
        this.id = id;
        return this.id;
    }

    public UUID getId() {
        return id;
    }

    public String getBody() {
        return body;
    }
}
