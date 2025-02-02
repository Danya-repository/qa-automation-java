package com.tcs.edu.utils;

import com.tcs.edu.domain.Message;

import java.util.Objects;

public class Helpers {

    public static boolean hasItemArrayItem(Message[] arr, Message item) {
        for (Message arrItem: arr) {
            if (arrItem == null) {continue;}

            if (Objects.equals(arrItem.getSeverityLevel(), item.getSeverityLevel()) &&
                    Objects.equals(arrItem.getBody(), item.getBody())) {
                return true;
            }
        }
        return false;
    }

    public static Message[] getArrayAfterPreprocessing(Message message, Message[] arrMessages) {
        Message[] templateArr;
        int index;

        if (message == null) {
            templateArr = new Message[arrMessages.length];
            index = 0;
        } else {
            templateArr = new Message[arrMessages.length + 1];
            index = 1;
            templateArr[0] = message;
        }

        if (arrMessages != null) {
            for (Message item:arrMessages) {
                templateArr[index] = item;
                index++;
            }
        }
        return templateArr;
    }

    public static int getLengthOfArrWithoutNulls(Message[] arrMessages) {
        if (arrMessages == null) { return 0; }
        int counter = arrMessages.length;

        for (Message arrItem: arrMessages) {
            if (arrItem.getBody() == null || arrItem.getSeverityLevel() == null) {
                counter--;
            }
        }
        return counter;
    }

    public static int getLengthOfArrWithoutDoubles(Message[] arr) {
        Message[] template = new Message[arr.length];
        int length = 0;

        for (int i = 0; i < arr.length; i++) {
            if(!hasItemArrayItem(template, arr[i])) {
                length++;
                template[i] = arr[i];
            }
        }
        return length;
    }

    public static Message[] getArrayWithoutDoubles(Message[] arr) {
        Message[] template = new Message[getLengthOfArrWithoutDoubles(arr)];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            if(!hasItemArrayItem(template, arr[i])) {
                template[index] = arr[i];
                index++;
            }
        }
        return template;
    }

    public static Message[] getReverseArr(Message[] arr) {
        Message[] template = new Message[arr.length];
        int index = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            template[index] = arr[i];
            index++;
        }
        return template;
    }
}
