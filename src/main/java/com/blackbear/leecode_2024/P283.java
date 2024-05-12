package com.blackbear.leecode_2024;

public class P283 {

    public static void main(String[] args) {
        Solution s = new Solution();

//        int[] nums = new int[]{0, 1, 0, 3, 12};
        int[] nums = new int[]{1, 0};

        s.moveZeroes(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    static class Solution {
        public void moveZeroes(int[] nums) {

            /**
             * 思路：
             * 1. 遇到0之后，和前面已经出现的0一起往后冒泡。
             * 只需要循环一次。
             * 2. 一个指针指向0数组的第一个位置。
             * 3. 一个flag 标志0的数量。
             */


            int length = nums.length;
            if (length <= 1) {
                return;
            }
            int zeroNum = 0;

            for (int index = 0; index < length; index++) {
                if (nums[index] == 0) {
                    zeroNum++;
                } else {
                    if (zeroNum > 0) {
                        nums[index - zeroNum] = nums[index];
                        nums[index] = 0;
                    }
                }

            }
        }
    }
}

