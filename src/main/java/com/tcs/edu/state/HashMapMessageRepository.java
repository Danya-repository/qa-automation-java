package com.tcs.edu.state;

import com.tcs.edu.constants.Severity;
import com.tcs.edu.domain.Message;
import com.tcs.edu.ifaces.MessageRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class HashMapMessageRepository implements MessageRepository {

    private Map<UUID, Message> messages = new HashMap();

    @Override
    public UUID create(Message message) {
        UUID id = message.setId(UUID.randomUUID());
        messages.put(id, message);

        return id;
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messages.get(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }

    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return messages.values().stream().
                filter(message -> message.getSeverityLevel() == by)
                .collect(Collectors.toList());
    }
}
