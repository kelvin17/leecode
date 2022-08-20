package com.blackbear.leetcode;

public class P34 {
    //[5,7,7,8,8,10]

    public static void main(String[] args) {
        int[] nums = new int[]{};
        Solution34 solution34 = new Solution34();
        int[] result = solution34.searchRange(nums, 6);
        System.out.printf("%d, %d\n", result[0], result[1]);
    }

}

class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int[] result = logNFind(nums, 0, nums.length - 1, target);
        return result == null ? new int[]{-1, -1} : result;
    }

    private int[] logNFind(int[] nums, int low, int high, int target) {
        if (low > high) {
            return null;
        }

        int mid = (low + high) / 2;

        if (nums[mid] > target) {
            high = mid - 1;
            return logNFind(nums, low, high, target);
        } else if (nums[mid] < target) {
            low = mid + 1;
            return logNFind(nums, low, high, target);
        }

        //nums[mid] == target
        int begin = mid;
        int end = mid;
        while (begin >= 1) {
            if (nums[begin - 1] == target) {
                begin = begin - 1;
            } else {
                break;
            }
        }

        while (end <= nums.length - 2) {
            if (nums[end + 1] == target) {
                end = end + 1;
            } else {
                break;
            }
        }

        return new int[]{begin, end};
    }
}
