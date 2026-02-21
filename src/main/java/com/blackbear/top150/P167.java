package com.blackbear.top150;

import java.util.Arrays;

public class P167 {

    public static void main(String[] args) {
        int[] numbers = new int[]{2,3,4};
        int target = 6;

        P167 p167 = new P167();
        int[] result = p167.twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{left + 1, right + 1};
    }
}
