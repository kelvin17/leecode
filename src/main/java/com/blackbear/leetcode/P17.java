package com.blackbear.leetcode;

import java.util.*;

public class P17 {

    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        solution17.letterCombinations("23");
    }
}


class Solution17 {

    final static Map<Integer, List<Character>> infoMap = new HashMap<>();

    static {
        infoMap.put(2, new ArrayList<>(Arrays.asList('a', 'b', 'c')));
        infoMap.put(3, new ArrayList<>(Arrays.asList('d', 'e', 'f')));
        infoMap.put(4, new ArrayList<>(Arrays.asList('g', 'h', 'i')));
        infoMap.put(5, new ArrayList<>(Arrays.asList('j', 'k', 'l')));
        infoMap.put(6, new ArrayList<>(Arrays.asList('m', 'n', 'o')));
        infoMap.put(7, new ArrayList<>(Arrays.asList('p', 'q', 'r', 's')));
        infoMap.put(8, new ArrayList<>(Arrays.asList('t', 'u', 'v')));
        infoMap.put(9, new ArrayList<>(Arrays.asList('w', 'x', 'y', 'z')));
    }

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        char[] digitArray = digits.toCharArray();
        int[] numArray = new int[digitArray.length];
        for (int index = 0; index < numArray.length; index++) {
            numArray[index] = Integer.valueOf(String.valueOf(digitArray[index]));
        }
        digui(new StringBuilder(), 0, numArray, result);

        for (String ii : result) {
            System.out.println(ii);
        }
        return result;
    }

    private void digui(StringBuilder parent, int level, int[] numArray, List<String> result) {
        if (level >= numArray.length) {
            return;
        }

        List<Character> currentList = infoMap.get(numArray[level]);

        for (char current : currentList) {
            StringBuilder currentSB = new StringBuilder(parent);
            currentSB.append(current);
            if (level == numArray.length - 1) {
                result.add(currentSB.toString());
            } else {
                digui(currentSB, level + 1, numArray, result);
            }
        }
    }
}
