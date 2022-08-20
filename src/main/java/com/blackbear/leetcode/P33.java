package com.blackbear.leetcode;

public class P33 {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        Solution33 solution33 = new Solution33();
        System.out.println(solution33.search(nums, 0));
    }
}

class Solution33 {
    public int search(int[] nums, int target) {
        int index = find(nums, 0, nums.length - 1, target);
        return index;
    }

//    private int find(int[] nums, int low, int high, int target) {
//
//        if (low >= high) {
//            if (high == low && nums[low] == target) {
//                return low;
//            }
//            return -1;
//        }
//
//        int index = -1;
//
//        //int mid = low + ((high - low) / 2);
//        int mid = (low + high) / 2;
//        if (nums[mid] == target) {
//            index = mid;
//        } else {
//            if (nums[mid] < target) {
//                //先找后半段
//                index = find(nums, mid + 1, high, target);
//                if (index == -1) {
//                    index = find(nums, low, mid - 1, target);
//                }
//            } else {
//                index = find(nums, low, mid - 1, target);
//                if (index == -1) {
//                    index = find(nums, mid + 1, high, target);
//                }
//            }
//        }
//
//        return index;
//    }


    private int find(int[] nums, int low, int high, int target) {

        if (low >= high) {
            if (high == low && nums[low] == target) {
                return low;
            }
            return -1;
        }

        int index = -1;

        //int mid = low + ((high - low) / 2);
        int mid = (low + high) / 2;
        if (nums[mid] == target) {
            index = mid;
        } else {
            //判断哪一段是有序的
            if (nums[low] <= nums[mid]) {
                //前半段有序
                if (nums[low] <= target && target < nums[mid]) {
                    //且 target在这一段中，则直接进入这一段找
                    index = find(nums, low, mid - 1, target);
                } else {
                    index = find(nums, mid + 1, high, target);
                }
            } else {
                //后半段有序
                if (nums[mid] < target && target <= nums[high]) {
                    //且target在这一段
                    index = find(nums, mid + 1, high, target);
                } else {
                    index = find(nums, low, mid - 1, target);
                }
            }
        }

        return index;
    }
}
