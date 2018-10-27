package com.wordsrate;

import java.util.Scanner;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Run with params: <pathToInFile> <wordsCount>");
        }

        try {
            var file = new File(args[0]);
            var input = new Scanner(file);
            var wordsQuantity = Integer.parseInt(args[1]);
            var wordsRating = new WordsRating(input, wordsQuantity);
            wordsRating.printPopularWords();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
