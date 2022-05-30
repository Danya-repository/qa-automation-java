package com.tcs.edu.printer;

import com.tcs.edu.ifaces.Printer;

/**
 * Класс {@link ConsolePrinter} используется для хранения методов и процедур, которые предназначены
 * для вывода данных в консоль.
 * Побочные эффекты отсуствуют, т.к методы и процедуры класса никак не изменяют входящие параметры.
 * @author     Daniil Kashapov
 * @version 1.0
 */

public class ConsolePrinter implements Printer {
    /**
     * Процедура {@link com.tcs.edu.printer.ConsolePrinter#print(String)} прездназначена для
     * вывода в консоль поступающего на вход параметра.
     * @param message ожидаемый на вход для исполнения процедуры, должен быть представлен типом String.
     *
     * Побочные эффекты отсуствуют, процедура {@link com.tcs.edu.printer.ConsolePrinter#print(String)}
     * не изменяет входящие параметры.
     */
    public void print(String message) {
        System.out.println(message);
    }
}
