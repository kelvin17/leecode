package com.blackbear.leetcode;

public class P53 {

    public static void main(String[] args) {

        int[] nums = new int[]{5, 4, -1, 7, 8};
        Solution53 solution53 = new Solution53();
        int result = solution53.maxSubArray_2(nums);
        System.out.println(result);
    }

}


class Solution53 {
    public int maxSubArray(int[] nums) {

        int length = nums.length;

        if (length == 1) {
            return nums[0];
        }
        int max = nums[0];
        for (int i = 0; i < length; i++) {

            int currentSum = nums[i];
            max = currentSum > max ? currentSum : max;

            for (int j = i + 1; j < length; j++) {
                currentSum += nums[j];
                max = currentSum > max ? currentSum : max;
            }
        }
        return max;
    }

    public int maxSubArray_2(int[] nums) {

        int len = nums.length;

        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < len; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = nums[i] + dp[i - 1];
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
