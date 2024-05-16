package com.blackbear.leecode_2024;

import java.util.Arrays;

public class P215 {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        System.out.println(s.findKthLargest(nums, 4));
    }

    static class Solution {
        public int findKthLargest(int[] nums, int k) {
            if (k > nums.length) {
                throw new RuntimeException("Params Error");
            }

            Arrays.sort(nums);

            return nums[nums.length - k];

        }
    }
}
