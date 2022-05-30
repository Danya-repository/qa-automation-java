package com.tcs.edu.printer;

import com.tcs.edu.constants.Severity;
import com.tcs.edu.domain.Message;
import com.tcs.edu.exceptions.LogException;
import com.tcs.edu.ifaces.MessageRepository;
import com.tcs.edu.ifaces.MessageService;
import com.tcs.edu.state.HashMapMessageRepository;

import java.util.Collection;
import java.util.UUID;

import static com.tcs.edu.utils.Helpers.*;

public class DecoratingMessageService extends ValidatedService implements MessageService {

    private MessageRepository messageRepository = new HashMapMessageRepository();

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messageRepository.findByPrimaryKey(key);
    }

    @Override
    public void create(Message message, Message... messages) {
        try {
            super.isArgsValid(message, messages);
        } catch (IllegalArgumentException e) {
            throw new LogException(message, e);
        }

        Message[] processArr;
        processArr = getArrayAfterPreprocessing(message, messages);

        for (Message messageItem: processArr) {
            messageRepository.create(messageItem);
        }
    }

    @Override
    public Collection<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return messageRepository.findBySeverity(by);
    }


}
