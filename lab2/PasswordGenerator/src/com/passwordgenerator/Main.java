package com.passwordgenerator;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong arguments");
            return;
        }

        int length = Integer.parseInt(args[0]);
        String mask = args[1];

        System.out.println(PasswordGenerator.getPassword(mask, length));
    }
}
