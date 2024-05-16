package com.blackbear.leecode_2024;

import java.util.Arrays;

public class P35 {

    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 6};
        System.out.println(Solution.searchInsert2(nums, 3));
    }

    static class Solution {

        public static int searchInsert2(int[] nums, int target) {
            int low = 0;
            int high = nums.length - 1;

            while (low <= high) {
                int mid = (low + high) >> 1;
                int midVal = nums[mid];

                if (midVal < target)
                    low = mid + 1;
                else if (midVal > target)
                    high = mid - 1;
                else
                    return mid; // key found
            }
            return low;  // key not found.
        }

        public static int searchInsert(int[] nums, int target) {
            Arrays.binarySearch(nums, target);

            //找到第一个不小于target的index
            return searchInsertInner(nums, 0, nums.length - 1, target);
        }

        public static int searchInsertInner(int[] nums, int lowIndex, int highIndex, int target) {
            //找不到情况1-比小的小
            if (target < nums[lowIndex]) {
                return lowIndex;
            }
            //找不到2
            if (target > nums[highIndex]) {
                return highIndex + 1;
            }

            int midIndex = (lowIndex + highIndex) / 2;
            if (target == nums[midIndex]) {
                return midIndex;
            } else if (target == nums[midIndex + 1]) {
                return midIndex + 1;
            } else {
                //1.找不到的特殊情况
                if (highIndex - lowIndex == 1) {
                    //落在中间
                    if (target > nums[lowIndex] && target < nums[highIndex]) {
                        return highIndex;
                    }
                }

                //需要继续折半的情况
                if (target < nums[midIndex]) {
                    return searchInsertInner(nums, lowIndex, midIndex, target);
                } else {
                    return searchInsertInner(nums, midIndex, highIndex, target);
                }
            }
        }
    }
}
