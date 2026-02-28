package com.blackbear.top150;

import java.util.Arrays;
import java.util.HashSet;

public class P128_Hashset {

    public static void main(String[] args) {
        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};

        P128_Hashset hashmap = new P128_Hashset();
        System.out.printf("The longest consecutive is: %d\n", hashmap.longestOn(nums));
    }

    public int longestOn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int max = 1;
        int cur;

        for (int num : set) {
            if (!set.contains(num-1)) {
                int next = num + 1;
                cur = 1;
                while (set.contains(next)) {
                    cur++;
                    max = Math.max(max, cur);
                    next = next + 1;
                }
            }
        }
        return max;
    }

    public int longestConsecutive(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums.length;
        }

        Arrays.sort(nums);

        int max = 1;
        int cur = 1;
        int preItem = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == (preItem + 1)) {
                cur += 1;
                max = Math.max(max, cur);
                preItem = nums[i];
            } else if (nums[i] > preItem + 1) {
                preItem = nums[i];
                cur = 1;
            }
        }
        return max;
    }

}
