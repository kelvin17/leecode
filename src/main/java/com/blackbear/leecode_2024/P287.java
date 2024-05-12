package com.blackbear.leecode_2024;

import java.util.Arrays;

public class P287 {

    public static void main(String[] args) {

        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(findDuplicate(nums));

    }

    public static int findDuplicate(int[] nums) {

        int fastIndex = 0;

        for (int slowIndex = 1; slowIndex < nums.length; slowIndex++) {
            if (nums[fastIndex] == nums[slowIndex]) {
                return nums[fastIndex];
            }
            fastIndex = fastIndex + 2;
        }

        throw new RuntimeException("不会出现");

    }

    public static int findDuplicate2(int[] nums) {

        // 排序
        Arrays.sort(nums);

        //查找
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }

        throw new RuntimeException();

    }


}
