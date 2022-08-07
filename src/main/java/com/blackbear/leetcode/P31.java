package com.blackbear.leetcode;

import java.util.Arrays;

public class P31 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 5};
        System.out.println("---------begin---------");
        for (int item : nums) {
            System.out.println(item);
        }
        System.out.println("---------end---------");
        Solution31 solution31 = new Solution31();
        solution31.nextPermutation(nums);
        System.out.println("---------begin---------");
        for (int item : nums) {
            System.out.println(item);
        }
        System.out.println("---------end---------");
    }
}


class Solution31 {
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return;
        }
        //初始化 后面区间的开始，结束是length-1
        int endScopeBegin = length - 1;

        //初始化帮助类
        int maxValue = nums[length - 1];

        int i = endScopeBegin - 1;
        for (; i >= 0; i--) {
            if (nums[i] < maxValue) {
                int goalIndex = endScopeBegin - 1;
                int leastMaxNum = nums[i];//大于nums[i] 的最小值

                for (int j = endScopeBegin; j < length; j++) {
                    if (nums[j] > nums[i]) {
                        if (goalIndex == i) {
                            //初始值
                            goalIndex = j;
                            leastMaxNum = nums[j];
                        } else {
                            if (nums[j] < leastMaxNum) {
                                leastMaxNum = nums[j];
                                goalIndex = j;
                            }
                        }
                    }
                }

                //swap
                nums[goalIndex] = nums[i];
                nums[i] = leastMaxNum;
                //对endScopeBegin 到结尾进行排序
                Arrays.sort(nums, endScopeBegin, length);
                return;//结束
            }
            //更新帮助类
            endScopeBegin--;
            if (nums[i] > maxValue) {
                maxValue = nums[i];
            }

        }

        //如果上面没有返回，则说明到了当前排列是最大排列了，所以返回最小排列，即nums重排
        Arrays.sort(nums);
    }
}