package com.blackbear.top150;

public class P11 {

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};

        P11 p11 = new P11();
        System.out.printf("MaxArea is %d\n", p11.maxArea(height));

    }

    public int maxArea(int[] height) {

        int volume = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            volume = Math.max(volume, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            }else {
                right--;
            }
        }


        return volume;

    }
}
