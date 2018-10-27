package com.wordsrate;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

class WordsRating {
    private LinkedHashMap<String, Integer> _wordsMap = new LinkedHashMap<>();
    private LinkedHashMap<Integer, LinkedHashSet<String>> _ratingMap = new LinkedHashMap<>();
    private int _maxSameWords = 0;
    private int _ratingWordsQuantity;


    WordsRating(Scanner input, Integer ratingWords) {
        _ratingWordsQuantity = ratingWords;
        prepareDataForPrint(input);
    }

    void printPopularWords() {
        int counter = 0;
        for (int i = _maxSameWords; i > 0; i--) {
            var set = _ratingMap.get(i);
            for (var value: set) {
                counter++;
                System.out.println(value + " " +  _wordsMap.get(value));
                if (counter == _ratingWordsQuantity) return;
            }
        }
    }

    private void prepareDataForPrint(Scanner input) {
        String word;
        Integer wordsCount;

        while (input.hasNext()) {
            word = input.next();
            if (_wordsMap.containsKey(word)) {
                wordsCount = _wordsMap.get(word) + 1;
                _wordsMap.put(word, wordsCount);
            } else {
                wordsCount = 1;
                _wordsMap.put(word, wordsCount);
            }
            _maxSameWords = wordsCount > _maxSameWords ? wordsCount: _maxSameWords;
            updateRatingMap(word, wordsCount);
        }
    }

    private void updateRatingMap(String word, Integer wordsCount) {
        if (_ratingMap.containsKey(wordsCount)) {
            LinkedHashSet<String> words = _ratingMap.get(wordsCount);
            LinkedHashSet<String> previousWords = _ratingMap.get(wordsCount - 1);
            words.add(word);
            if (previousWords != null) {
                previousWords.remove(word);
            }
        } else {
            var newWordsSet = new LinkedHashSet<String>();
            newWordsSet.add(word);
            _ratingMap.put(wordsCount, newWordsSet);
            LinkedHashSet<String> previousWords = _ratingMap.get(wordsCount - 1);
            if (previousWords != null) {
                previousWords.remove(word);
            }
        }
    }}
