package com.tcs.edu.utils;

import java.util.Objects;

public class Helpers {

    public static boolean hasItemArrayItem(String[] arr, String str) {
        for (String arrItem: arr) {
            if (Objects.equals(arrItem, str)) {
                return true;
            }
        }
        return false;
    }

    public static String[] getArrayAfterPreprocessing(String message, String[] arr) {
        String[] templateArr;
        int index;

        if (message == null) {
            templateArr = new String[getLengthOfArrWithoutNulls(arr)];
            index = 0;
        } else {
            templateArr = new String[getLengthOfArrWithoutNulls(arr) + 1];
            index = 1;
            templateArr[0] = message;
        }

        if (arr != null) {
            for (String item:arr) {
                if (item != null) {
                    templateArr[index] = item;
                    index++;
                }
            }
        }
        return templateArr;
    }

    public static int getLengthOfArrWithoutNulls(String[] arr) {
        if (arr == null) { return 0; }
        int counter = arr.length;

        for (String arrItem: arr) {
            if (arrItem == null) {
                counter--;
            }
        }
        return counter;
    }

    public static int getLengthOfArrWithoutDoubles(String[] arr) {
        String[] template = new String[arr.length];
        int length = 0;

        for (int i = 0; i < arr.length; i++) {
            if(!Helpers.hasItemArrayItem(template, arr[i])) {
                length++;
                template[i] = arr[i];
            }
        }
        return length;
    }

    public static String[] getArrayWithoutDoubles(String[] arr) {
        String[] template = new String[getLengthOfArrWithoutDoubles(arr)];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            if(!Helpers.hasItemArrayItem(template, arr[i])) {
                template[index] = arr[i];
                index++;
            }
        }
        return template;
    }

    public static String[] getReverseArr(String[] arr) {
        String[] template = new String[arr.length];
        int index = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            template[index] = arr[i];
            index++;
        }
        return template;
    }
}
