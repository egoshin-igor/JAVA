package com.caesarcipher;

import com.caesarcipher.com.caesarcipher.enums.CipherMode;

public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Correct run: program.exe <cipherMode> <key> <string>");
            return;
        }
        CipherMode cipherMode;
        switch (args[0]) {
            case "-e":
                cipherMode = CipherMode.Encoding;
                break;
            case "-d":
                cipherMode = CipherMode.Decoding;
                break;
            default:
                System.out.println("cipherMode is \"-e\" or \"-d\"");
                return;
        }
        var originString = args[2];
        int key;
        try {
            key = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        String cipher = CaesarCipher.getCipher(originString, cipherMode, key);
        if (cipher != null) {
            System.out.println(cipher);
        } else {
            System.out.println("Decrypted\\encrypted string has non-english chars");
        }
    }
}
