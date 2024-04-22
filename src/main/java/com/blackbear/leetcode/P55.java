package com.blackbear.leetcode;

public class P55 {

    public static void main(String[] args) {

        int[] nums = new int[]{3, 2, 1, 0, 4};
        Solution55 solution55 = new Solution55();
        System.out.println(solution55.canJump(nums));
    }
}

class Solution55 {
    /**
     * 动态规划：
     *  推导关系：
     *      第i位可达依赖前序满足如下条件：
     *          第i-1位可达，且num[i-1] >= 1
     *          或 第i-2位可达，且num[i-2] >= 2
     *          或 第i-3位可达，且num[i-2] >= 2
     *          ………………………………
     *          或 第0位可达（肯定），且num[0] >= i
     *
     *   为了减少计算量。在求解的时候，正序进行。即先求第1位。第0位必然可达
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        int len = nums.length;

        if (len <= 1) {
            return true;
        }

        //记录每一位
        boolean dp[] = new boolean[len];
        for (int i = 0; i < len; i++) {
            dp[i] = false;
        }

        dp[0] = true;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] == true && nums[j] >= i - j) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len - 1];
    }
}