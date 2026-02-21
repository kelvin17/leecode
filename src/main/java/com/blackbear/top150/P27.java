package com.blackbear.top150;

public class P27 {

    public static void main(String[] args) {

    }

    class Solution {
        public int removeElement(int[] nums, int val) {
            int tail = nums.length; // point to the next element after that different to val

            for (int i = 0; i < tail; i++) {
                if (nums[i] == val) {
                    while (nums[tail - 1] == val) {
                        tail--;
                    }
                    nums[i] =  nums[tail-1];
                    tail--;
                }
            }


            return tail;
        }
    }
}



