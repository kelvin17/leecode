package com.blackbear.leecode_2024;

import java.util.Arrays;

public class P136 {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] nums = new int[]{4, 1, 2, 1, 2};
        System.out.println(s.singleNumber2(nums));
    }

    static class Solution {

        public int singleNumber2(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }

            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                result = result ^ nums[i];
            }

            return result;
        }

        public int singleNumber(int[] nums) {

            int result = 0;
            if (nums.length == 1) {
                result = nums[0];
            } else {
                Arrays.sort(nums);

                for (int n = 0; n < nums.length; n = n + 2) {
                    if (n == nums.length - 1) {
                        result = nums[n];
                        break;
                    }

                    if (nums[n] != nums[n + 1]) {
                        result = nums[n];
                        break;
                    }
                }
            }
            return result;
        }
    }
}
