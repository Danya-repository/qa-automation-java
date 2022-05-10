package com.tcs.edu.decorator;

import com.tcs.edu.constants.Severity;
import com.tcs.edu.ifaces.MessageDecorator;
import java.time.Instant;

/**
 * Класс {@link TimestampMessageDecorator} предназначен для хранения декорирующих процедур по добавлению к входящим
 * данным времени.
 * Побочные эффекты отсуствуют, т.к методы и процедуры класса никак не изменяют входящие параметры, а возвращают
 * новые данные.
 * @author     Daniil Kashapov
 * @version 1.0
 */

public class TimestampMessageDecorator implements MessageDecorator {

    /**
     * Процедура {@link com.tcs.edu.decorator.TimestampMessageDecorator#decorate(String)} возвращает новую строку
     * состоящую из текущего времени, которое берётся
     * из метода {@link Instant#now()} и входящего параметра,
     * также процедура добавляет к новой строке специальный символ переноса если вызов процедуры является кратным
     * константе PAGE_SIZE.
     *
     * @param severity ожидаемый на вход для исполнения процедуры, должен быть представлен типом Severity.
     * @param body ожидаемый на вход для исполнения процедуры, должен быть представлен типом String.
     *
     * Побочные эффекты отсуствуют, процедура {@link com.tcs.edu.decorator.TimestampMessageDecorator#decorate(String)}
     * не изменяет входящие параметры.
     *
     * В процедуре используется поясняющая переменная decoratedMessage для повешыния читабельности.
     * Глобальная переменная messageCount нужна для фиксации колиества вызова процедуры decorate
     *
     * В процедуре используется константа PAGE_SIZE, которая
     */

    static Integer messageCount = 0;
    static final Integer PAGE_SIZE = 2;

    @Override
    public String decorate(String body, Severity severity) {
        return null;
    }

    @Override
    public String decorate(String body) {
        messageCount++;

        if (messageCount % PAGE_SIZE == 0) {
            String decoratedMessage = String.format("%d %s %s\n---", messageCount, Instant.now(), body);
            return decoratedMessage;
        }

        String decoratedMessage = String.format("%d %s %s", messageCount, Instant.now(), body);
        return decoratedMessage;
    }
}
