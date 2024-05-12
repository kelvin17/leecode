package com.blackbear.leecode_2024;

import org.junit.experimental.max.MaxCore;

public class P42 {

}

class Solution {
    public int trap(int[] height) {

        int sum = 0;
        int lenght = height.length;
        int[] max_left = new int[lenght];//会被初始化为0
        int[] max_right = new int[lenght];//会被初始化为0

        //1. 保存max_left[i]
        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }

        //2. 保存max_right[i]
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(height[i + 1], max_right[i + 1]);
        }

        //3. 计算每一列可能得存水量-最后一列和第一列都不会有水，不用算了
        for (int i = 1; i < height.length - 1; i++) {
            int low_wall = Math.min(max_left[i], max_right[i]);
            if (height[i] < low_wall) {
                sum = sum + (low_wall - height[i]);
            }
        }

        return sum;
    }

    public int trap2(int[] height) {

        int sum = 0;
        int lenght = height.length;
//        int[] max_left = new int[lenght];//会被初始化为0
        int max_left = 0;
        int[] max_right = new int[lenght];//会被初始化为0

        //1. 保存max_left[i]
//        for (int i = 1; i < height.length - 1; i++) {
//            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
//        }

        //2. 保存max_right[i]
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(height[i + 1], max_right[i + 1]);
        }

        //3. 计算每一列可能得存水量-最后一列和第一列都不会有水，不用算了
        for (int i = 1; i < height.length - 1; i++) {
            max_left = Math.max(max_left, height[i - 1]);

            int low_wall = Math.min(max_left, max_right[i]);
            if (height[i] < low_wall) {
                sum = sum + (low_wall - height[i]);
            }
        }

        return sum;
    }

    public int trap3(int[] height) {

        int sum = 0;
        int lenght = height.length;
//        int[] max_left = new int[lenght];//会被初始化为0
        int max_left = height[0];
//        int[] max_right = new int[lenght];//会被初始化为0
        int max_right = height[lenght];

        int left = 1;
        int right = height.length - 2;

        //3. 计算每一列可能得存水量-最后一列和第一列都不会有水，不用算了
        for (int i = 1; i < height.length - 1; i++) {
            if (height[left - 1] < height[right + 1]) {

            } else {

            }

            int low_wall = Math.min(max_left, max_right);
            if (height[i] < low_wall) {
                sum = sum + (low_wall - height[i]);
            }
        }

        return sum;
    }
}
