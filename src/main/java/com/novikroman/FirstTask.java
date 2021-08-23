package com.novikroman;


import java.util.*;

public class FirstTask {
    /**
     * Method for simplifying English text
     */
    public static String simplifyText(String text) {
        return removeWords(removeLetterE(removeDuplicates(replaceLetterC(text))));
    }

    /**
     * Method for replacing the letter C
     */
    private static String replaceLetterC(String text) {
        char[] arrayOfLetters = text.toLowerCase().toCharArray();
        StringBuilder alterText = new StringBuilder();
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < arrayOfLetters.length; i++) {
            map.put(i, arrayOfLetters[i]);
        }

        for (int i = 0; i < map.size() - 1; i++) {
            char currentLetter = map.get(i);
            char nextLetter = map.get(i + 1);
            if (currentLetter == 'c') {
                switch (nextLetter) {
                    case 'i':
                    case 'e':
                        map.put(i, 's');
                        break;
                    default:
                        map.put(i, 'k');
                }
            }
        }
        map.values().forEach(alterText::append);

        return alterText.toString();
    }

    /**
     * Method for removing duplicate letters in text
     */
    private static String removeDuplicates(String text) {
        char[] arrayOfLetters = text.toCharArray();
        StringBuilder alterText = new StringBuilder();
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < arrayOfLetters.length; i++) {
            map.put(i, arrayOfLetters[i]);
        }

        for (int i = 0; i < map.size() - 1; i++) {
            char currentLetter = map.get(i);
            char nextLetter = map.get(i + 1);
            if (currentLetter == 'e') {
                if (nextLetter == 'e') {
                    map.put(i, null);
                    map.put(i + 1, 'i');
                }
            } else if (currentLetter == 'o') {
                if (nextLetter == 'o') {
                    map.put(i, null);
                    map.put(i + 1, 'u');
                }
            } else if (currentLetter == nextLetter) {
                map.put(i, null);
            }
        }

        map.values().stream().filter(Objects::nonNull).forEach(alterText::append);
        return alterText.toString();
    }

    /**
     * Method to remove the letter E at the end of a word
     */
    private static String removeLetterE(String text) {
        String[] arrayOfLetters = text.split(" ");
        StringBuilder alterText = new StringBuilder();
        for (String arrayOfLetter : arrayOfLetters) {
            if (arrayOfLetter.length() > 1) {
                if (arrayOfLetter.endsWith("e")) {
                    String abc = arrayOfLetter.substring(0, arrayOfLetter.length() - 1);
                    alterText.append(abc).append(" ");
                } else {
                    alterText.append(arrayOfLetter).append(" ");
                }
            } else {
                alterText.append(arrayOfLetter).append(" ");
            }
        }
        return alterText.toString().trim();
    }

    /**
     * Method to remove "a, an, the," from text
     */
    private static String removeWords(String text) {
        String[] arrayOfWords = text.split(" ");
        StringBuilder alterText = new StringBuilder();
        for (String word : arrayOfWords) {
            switch (word) {
                case "a":
                case "an":
                case "th":
                    break;
                default:
                    alterText.append(word).append(" ");
                    break;
            }
        }
        return alterText.toString().trim();
    }

}

