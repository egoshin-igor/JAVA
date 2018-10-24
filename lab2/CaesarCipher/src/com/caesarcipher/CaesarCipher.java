package com.caesarcipher;

import com.caesarcipher.com.caesarcipher.enums.CipherMode;

class CaesarCipher {
    private final static int FIRST_LOWER_CHAR_CODE = 97;
    private final static int LAST_LOWER_CHAR_CODE = 122;
    private final static int FIRST_UPPER_CHAR_CODE = 65;
    private final static int LAST_UPPER_CHAR_CODE = 90;
    private final static int ALPHABET_SIZE = 26;

    static String getCipher(String originString, CipherMode mode, int key) {
        if (!validateOriginString(originString)) {
            return null;
        }

        String result;
        if (mode == CipherMode.Decoding) {
            result = getDecryptedString(originString, key);
        } else {
            result = getEncryptedString(originString, key);
        }

        return result;
    }

    private static String getEncryptedString(String originString, int key) {
        var stringBuilder = new StringBuilder();
        for (int i = 0; i < originString.length(); i++) {
            stringBuilder.append(getEncryptedChar(originString.charAt(i), key));
        }

        return stringBuilder.toString();
    }

    private static String getDecryptedString(String originString, int key) {
        return getEncryptedString(originString, -key);
    }

    private static char getEncryptedChar(char originChar, int key) {
        int charCode = (int) originChar;
        boolean isLowerChar = charCode >= FIRST_LOWER_CHAR_CODE && charCode <= LAST_LOWER_CHAR_CODE;
        key = key < 0 ? ALPHABET_SIZE + (key % ALPHABET_SIZE) : key;
        if (isLowerChar) {
            int charPosition = charCode - FIRST_LOWER_CHAR_CODE;
            int newPosition = ((key % ALPHABET_SIZE) + charPosition) % ALPHABET_SIZE;
            return (char) (FIRST_LOWER_CHAR_CODE + newPosition);
        } else {
            int charPosition = charCode - FIRST_UPPER_CHAR_CODE;
            int newPosition = ((key % ALPHABET_SIZE) + charPosition) % ALPHABET_SIZE;
            return (char) (FIRST_UPPER_CHAR_CODE + newPosition);
        }
    }

    private static boolean validateOriginString(String originString) {
        int charCode;
        boolean isEnglishChar;
        for (int i = 0; i < originString.length(); i++) {
            charCode = (int) originString.charAt(i);
            isEnglishChar = (charCode >= FIRST_LOWER_CHAR_CODE && charCode <= LAST_LOWER_CHAR_CODE)
                    || (charCode >= FIRST_UPPER_CHAR_CODE && charCode <= LAST_UPPER_CHAR_CODE);
            if (!isEnglishChar) {
                return false;
            }
        }

        return true;
    }
}
