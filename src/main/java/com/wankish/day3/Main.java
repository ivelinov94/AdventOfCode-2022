package com.wankish.day3;

import com.wankish.utils.FileReader;
import com.wankish.utils.Logger;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Map<Character, Integer> lowercaseValues = new HashMap<>();
    private static final Map<Character, Integer> uppercaseValues = new HashMap<>();
    public static void main(String[] args) throws URISyntaxException {
        FileReader reader = new FileReader();
        List<String> dataset = reader.readFile("day3.txt");
        initDAO();
        int totalScore = 0;
        int total2Score = 0;
        for(int i = 1; i <= dataset.size(); i++) {
            String set = dataset.get(i - 1);
            int middlePoint = set.length() / 2;
            String first = set.substring(0, middlePoint);
            String second = set.substring(middlePoint);
            totalScore += getScore(first, second);

            if (i % 3 == 0) {
                int current = i - 1;
                total2Score += getScore2Task(dataset.get(current), dataset.get(current - 1), dataset.get(current - 2));
            }
        }

        Logger.log("Total Score: " + totalScore);
        Logger.log("Total2  Score: " + total2Score);
    }

    private static void initDAO() {
        char small = 'a';
        int smallIndex = 1;
        for(; small <= 'z'; ++small, smallIndex++) {
            lowercaseValues.put(small, smallIndex);
        }

        char big = 'A';
        int bigIndex = 27;
        for(; big <= 'Z'; ++big, bigIndex++) {
            uppercaseValues.put(big, bigIndex);
        }
    }

    private static int getScore2Task(String s, String s1, String s2) {
            for(int i =0; i < s.length(); i++) {
                char currentChar = s.charAt(i);
                int index = s1.indexOf(currentChar);
                int index2 = s2.indexOf(currentChar);
                if(index != -1 && index2 != -1) {
                    return getValueOf(currentChar);
                }
            }

            return 0;
    }

    private static int getScore(String first, String second) {
        for(int i = 0; i < first.length(); i++) {
            char currentChar = first.charAt(i);
            int secondChar = second.indexOf(currentChar);
            if(secondChar != -1) {
                return getValueOf(currentChar);
            }
        }
        return 0;
    }

    private static int getValueOf(char duplicate) {
        boolean isSmall = lowercaseValues.containsKey(duplicate);
        if(isSmall) {
            return lowercaseValues.get(duplicate);
        }

        return uppercaseValues.get(duplicate);
    }
}
