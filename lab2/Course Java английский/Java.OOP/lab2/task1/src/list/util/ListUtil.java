package list.util;

import java.util.ArrayList;

public class ListUtil {
  public static ArrayList<Byte> getByteList(String str) throws NumberFormatException {
    if (str.length() != 0 && str.charAt(str.length() - 1) == '.') {
      throw new NumberFormatException();
    }

    String[] strArray = str.split("[.]");

    for(String s: strArray) {
      int charCode;
      for (int i = 0; i < s.length(); i++) {
        charCode = (int) s.charAt(i);
        if (charCode < 48 || charCode > 57) {
          throw new NumberFormatException();
        }
      }
    }

    int number;
    ArrayList<Byte> result = new ArrayList<>();
    for (String value : strArray) {
      number = Integer.parseInt(value);
      if (number < 0 || number > 255) {
        throw new NumberFormatException("Can't parse number " + Integer.toString(number) + " to byte");
      }

      result.add((byte) number);
    }

    return result;
  }

  public static String getString(ArrayList<Byte> byteList, char delimiter) {
    StringBuilder stringBuilder = new StringBuilder();

    int listSize = byteList.size();
    for (int i = 0; i < listSize; i++) {
      Byte byteNumber = byteList.get(i);
      int intNumber = (int) byteNumber & 0xFF;
      stringBuilder.append(intNumber);
      if (i + 1 != listSize) {
        stringBuilder.append(delimiter);
      }
    }

    return stringBuilder.toString();
  }
}
