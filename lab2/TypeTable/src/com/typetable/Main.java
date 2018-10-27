package com.typetable;

public class Main {

    public static void main(String[] args) {
        System.out.printf("%s%s%s%s%n", "Type", "\tMin", "\t\t\t\t\t\tMax", "\t\t\t\t\t\tSize");
        System.out.printf("%s\t%d\t%d\t\t%d%n", "Long", Long.MIN_VALUE, Long.MAX_VALUE, Long.BYTES);
        System.out.printf("%s\t%d\t\t\t\t%d\t\t\t\t%d%n", "Integer", Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.BYTES);
        System.out.printf("%s\t%d\t\t\t\t\t%d\t\t\t\t\t%d%n", "Short", Short.MIN_VALUE, Short.MAX_VALUE, Short.BYTES);
        System.out.printf("%s\t%d\t\t\t\t\t%d\t\t\t\t\t\t%d%n", "Byte", Byte.MIN_VALUE, Byte.MAX_VALUE, Byte.BYTES);
        System.out.printf("%s\t%s\t\t\t\t%s\t%d%n", "Double", Double.MIN_VALUE, Double.MAX_VALUE, Double.BYTES);
        System.out.printf("%s\t%s\t\t\t\t\t%s\t\t\t%d%n", "Float", Float.MIN_VALUE, Float.MAX_VALUE, Float.BYTES);
        System.out.printf("%s\t%d\t\t\t\t\t\t%d\t\t\t\t\t%d%n", "Char", (int) Character.MIN_VALUE, (int) Character.MAX_VALUE, Character.BYTES);
    }
}
