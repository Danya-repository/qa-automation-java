package com.tcs.edu.ifaces;

import com.tcs.edu.constants.Doubling;
import com.tcs.edu.constants.MessageOrder;
import com.tcs.edu.domain.Message;

public interface MessageService {

    void processMessage(Message message, Message... messages);

    void processMessage(MessageOrder order, Message message, Message... messages);

    void processMessage(MessageOrder order,
                        Doubling doubling, Message message, Message... messages);
}
