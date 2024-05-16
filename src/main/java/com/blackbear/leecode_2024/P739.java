package com.blackbear.leecode_2024;

public class P739 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] temp = new int[]{30, 60, 90};
        int[] result = s.dailyTemperatures(temp);

        for (int num : result) {
            System.out.print(num + " ");

        }

    }

    static class Solution {
        public int[] dailyTemperatures(int[] temperatures) {

            int length = temperatures.length;

            int[] result = new int[length];

            if (length == 1) {
                result[0] = 0;
                return result;
            }

            result[length - 1] = 0;
            for (int index = 0; index < temperatures.length - 1; index++) {

                int count = 0;
                for (int j = index + 1; j < temperatures.length; j++) {
                    if (temperatures[j] > temperatures[index]) {
                        count++;
                        result[index] = count;
                        break;
                    } else {
                        count++;
                    }
                }
            }

            return result;

        }
    }
}
