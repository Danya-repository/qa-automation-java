package com.tcs.edu.decorator;

import com.tcs.edu.constants.Severity;
import com.tcs.edu.ifaces.MessageDecorator;

/**
 * Класс {@link SeverityDecorator} предназначен для хранения процедур по добавлению к входящим данным уровня серьёзности.
 * Побочные эффекты отсуствуют, т.к методы и процедуры класса никак не изменяют входящие параметры, а возвращают
 * новые данные.
 * @author     Daniil Kashapov
 * @version 1.0
 */

public class SeverityDecorator implements MessageDecorator {

    /**
     * Процедура {@link com.tcs.edu.decorator.SeverityDecorator#decorate(String)} возвращает новую строку
     * состоящую из сообщения поступившего на вход в параметре message и уровня серьёзности поступившего на вход
     * в параметре severity
     *
     * @param body ожидаемый на вход для исполнения процедуры, должен быть представлен типом String.
     *
     * Побочные эффекты отсуствуют, процедура {@link com.tcs.edu.decorator.SeverityDecorator#decorate(String)}
     * не изменяет входящие параметры.
     * При поступлении в качестве severity значения MINOR происходит возврат сообщения message и строки "()"
     * При поступлении в качестве severity значения REGULAR происходит возврат сообщения message и строки "(!)"
     * При поступлении в качестве severity значения MAJOR происходит возврат сообщения message и строки "(!!!)"
     */

    @Override
    public String decorate(String body) {
        return null;
    }

    @Override
    public String decorate(String body, Severity severity) {
        String severityString = null;

        switch (severity) {
            case MINOR:
                severityString = "()";
                break;
            case REGULAR:
                severityString = "(!)";
                break;
            case MAJOR:
                severityString = "(!!!)";
                break;
            default:
                severityString = "(not found severity)";
        }

        return String.format("%s %s", body, severityString);
    }
}
