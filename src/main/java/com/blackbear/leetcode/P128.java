package com.blackbear.leetcode;

import java.util.Arrays;

public class P128 {

    class Solution {
        public static int longestConsecutive(int[] nums) {
//        1. 先排序
//        2. 开始遍历

            //边界条件
            if (nums.length <= 1) {
                return nums.length;
            }

            int maxConsecutive = 1;
            int currentConsecutive = 1;

            Arrays.sort(nums);

            for (int index = 1; index < nums.length; index++) {

                if (nums[index] - nums[index - 1] == 1) {
                    currentConsecutive++;
                } else if (nums[index] - nums[index - 1] == 0) {
                } else {
                    currentConsecutive = 1;
                }

                maxConsecutive = (currentConsecutive > maxConsecutive ? currentConsecutive : maxConsecutive);

            }

            return maxConsecutive;
        }
    }

    public static void main(String[] args) {

//        int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums = {1, 2, 0, 1};
//        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        System.out.println(Solution.longestConsecutive(nums));
    }

}
