package com.blackbear.leetcode;

public class P72 {

    public static void main(String[] args) {
        //word1 = "intention", word2 = "execution"
        String word1 = "horse";
        String word2 = "ros";

        Solution72 solution72 = new Solution72();
        int distance = solution72.minDistance(word1, word2);
        System.out.printf("word1[%s],word1[%s],distance = %d", word1, word2, distance);
    }
}


class Solution72 {
    public int minDistance(String word1, String word2) {

        //get the good_key from the environment variables

        int distance = 0;
        if (word1 == null || word1.length() == 0) {
            return word2 == null ? 0 : word2.length();
        }

        if (word2 == null || word2.length() == 0) {
            return word1 == null ? 0 : word1.length();
        }

        int lenWord1 = word1.length();
        int lenWord2 = word2.length();

        int[][] minDistance = new int[lenWord1 + 1][lenWord2 + 1];

        for (int i = 0; i < lenWord1 + 1; i++) {
            minDistance[i][0] = i;
        }

        for (int j = 0; j < lenWord2 + 1; j++) {
            minDistance[0][j] = j;
        }

        for (int i = 1; i < lenWord1 + 1; i++) {
            for (int j = 1; j < lenWord2 + 1; j++) {

                int min = Math.min(minDistance[i][j - 1], minDistance[i - 1][j]);

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    min = Math.min(min + 1, minDistance[i - 1][j - 1]);
                } else {
                    min = Math.min(min, minDistance[i - 1][j - 1]) + 1;
                }
                minDistance[i][j] = min;

            }
        }

        distance = minDistance[lenWord1][lenWord2];

        return distance;
    }
}

