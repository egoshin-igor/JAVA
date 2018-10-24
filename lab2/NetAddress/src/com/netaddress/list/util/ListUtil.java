package com.netaddress.list.util;

import java.util.ArrayList;

public class ListUtil {
    public static ArrayList<Byte> getByteList(String str) throws NumberFormatException {
        String[] strArray = str.split("[.]");

        int number;
        var result = new ArrayList<Byte>();
        for (var value : strArray) {
            number = Integer.parseInt(value);
            if (number < 0 || number > 255) {
                throw new NumberFormatException("Can't parse number " + Integer.toString(number) + " to byte");
            }

            result.add((byte) number);
        }

        return result;
    }

    public static String getString(ArrayList<Byte> byteList, char delimiter) {
        var stringBuilder = new StringBuilder();

        int listSize = byteList.size();
        for (int i = 0; i < listSize; i++) {
            var byteNumber = byteList.get(i);
            var intNumber = (int) byteNumber & 0xFF;
            stringBuilder.append(intNumber);
            if (i + 1 != listSize) {
                stringBuilder.append(delimiter);
            }
        }

        return stringBuilder.toString();
    }
}
