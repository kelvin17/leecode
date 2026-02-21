package com.blackbear.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P15 {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        if (nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            //in order to improve the time performance and avoid the repetition
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    boolean flag = true;
                    for (List<Integer> list : res) {
                        if (list.get(0) == nums[i] && list.get(1) == nums[j] && list.get(2) == nums[k]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                    //in order to improve the time performance and avoid the repetition
                    while (j < k && nums[j] == nums[j + 1]) {
                        j = j + 1;
                    }
                    while (j < k && nums[k - 1] == nums[k]) {
                        k = k - 1;
                    }
                    j = j+1;
                    k = k-1;
                } else if (sum > 0) {
                    k = k - 1;
                } else {
                    j = j + 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        int[] nums = new int[]{-1, 0, 1, 2, -1, 4};
//        int[] nums = new int[]{0,0,0};
        P15 p15 = new P15();
        List<List<Integer>> res = p15.threeSum(nums);
        System.out.println(res);
    }
}
