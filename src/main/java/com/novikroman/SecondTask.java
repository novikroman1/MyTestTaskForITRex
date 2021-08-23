package com.novikroman;

import sun.font.DelegatingShape;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SecondTask {
    private static boolean right = true;
    private static boolean left = true;
    private static boolean down = true;
    private static boolean isWin = false;
    private static List<Character[][]> matrix;

//    SecondTask.informationFromMaze("D:\\ExFile.txt");
    /**
     * Method for finding the transit time of a matrix
     * The method is unfinished.
     * Shows what he could do.
     */
    private int exitFromMaze(List<Character[][]> matrix, int levelBlock, int height, int width) {
        int currentLevel = 1;
        int currentHeight = 0;
        int currentWidth = 0;
        List<Element> recordMoves = new ArrayList<>(); // не делал Стек для упрощения
        recordMoves.add(new Element(currentLevel, currentHeight, currentWidth));

        while (!isWin) {
            Element previousElement = recordMoves.get(recordMoves.size() - 1);
            Character element = matrix.get(previousElement.level)[previousElement.height][previousElement.width];
            if (down) {
                if (currentHeight != height - 1) {
                    if (checkValue(currentLevel, currentHeight + 1, currentWidth)) {
                        recordMoves.add(new Element(currentLevel, ++currentHeight, currentWidth));
                    }
                } else if (currentHeight == height - 1 & currentLevel != levelBlock) {
                    if (checkValue(currentLevel, currentHeight + 1, currentWidth)) {//здесь при перескоке, можно упереться в ноль
                        currentHeight = 0;
                        recordMoves.add(new Element(++currentLevel, currentHeight, currentWidth));
                    }
                } else {
                    down = false;
                    break;
                }
            } else if (left) {
                if (currentWidth != 0) {
                    if (checkValue(currentLevel, currentHeight, currentWidth - 1)) {
                        recordMoves.add(new Element(currentLevel, currentHeight, --currentWidth));
                    }
                } else {
                    left = false;
                    break;
                }
            } else if (right) {
                if (currentWidth != width - 1) {
                    if (checkValue(currentLevel, currentHeight, currentWidth + 1)) {
                        recordMoves.add(new Element(currentLevel, currentHeight, ++currentWidth));
                    }
                } else {
                    right = false;
                    break;
                }
            } else {
                recordMoves.remove(previousElement.level);
                resetDirection();
            }
        }
        return recordMoves.size() * 5;
    }

    /**
     * Complete information about the maze
     */
    public static void informationFromMaze(String pathToFile) {
        List<String> allLineFromFile = getAllFromFile(pathToFile);
        List<Integer> params = getParam(allLineFromFile.get(0));
        int countBlocks = params.get(0);
        int height = params.get(1);
        int width = params.get(2);
        matrix = getMatrixForMaze(allLineFromFile, countBlocks, height, width);
    }

    /**
     * Get lines from a file
     */
    private static List<String> getAllFromFile(String pathToFile) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Get parameters level, M, H
     */
    private static List<Integer> getParam(String param) {
        List<Integer> myParam = new ArrayList<>();
        for (char c : param.toCharArray()) {
            if (c != 32)
                myParam.add(Integer.parseInt(String.valueOf(c)));
        }
        return myParam;
    }

    /**
     * Get a list of matrices from a list of strings
     */
    private static List<Character[][]> getMatrixForMaze(List<String> lines, int countBlocks, int height, int width) {
        List<Character[][]> listLines = new ArrayList<>();
        int countLines = 1;
        for (int i = 0; i < countBlocks; i++) {
            Character[][] matrix = new Character[height][width];
            for (int j = 0; j < height; j++) {
                char[] arrayChars = lines.get(countLines).toCharArray();
                countLines++;
                for (int k = 0; k < width; k++) {
                    matrix[j][k] = arrayChars[k];
                }
            }
            listLines.add(matrix);
        }
        return listLines;
    }

    /**
     * Reset all directions
     */
    private static void resetDirection() {
        right = true;
        left = true;
        down = true;
    }

    /**
     * Сhecking matrix value
     */
    private static boolean checkValue(int level, int height, int width) {
        Character symbol = matrix.get(level)[height][width];
        switch (symbol) {
            case '.':
                return true;
            case 'o':
                return false;
            default:
                isWin = true;
                return false;
        }
    }

    /**
     * Class for recording the state of matrix elements
     */
    class Element {
        int level;
        int height;
        int width;

        public Element(int level, int height, int width) {
            this.level = level;
            this.height = height;
            this.width = width;
        }
    }


}
