package com.blackbear.leetcode;

import java.util.*;

public class P42 {

    public static void main(String[] args) {
        int[] height = new int[]{5, 4, 1, 2};
        Solution42_2 solution42 = new Solution42_2();
        System.out.printf("the capacity is %d \n", solution42.trap(height));
    }
}

class Solution42 {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int capacity = 0;
        Deque<Integer> peekList = divideArea(height);

        while (!peekList.isEmpty()) {
            int begin = peekList.pop();

            if (peekList.isEmpty()) {
                break;
            }
            int end = peekList.peek();
            capacity += capacityInArea(height, begin, end);
        }

        return capacity;
    }

    /**
     * area0: begin0,end0
     * area1: begin1,end1
     * area2: begin2,end2
     * <p>
     * 每个分割点就是比两边都高
     *
     * @param height
     * @return
     */
    public Deque<Integer> divideArea(int[] height) {

        List<Integer> peekList = new ArrayList<>();

        for (int current = 0; current < height.length; current++) {
            if (current == 0) {
                if (height[current] > height[current + 1]) {
                    peekList.add(current);
                }
            } else if (current == height.length - 1) {
                if (height[current] > height[current - 1]) {
                    peekList.add(current);
                }
            } else if ((height[current - 1] < height[current]) && height[current] >= height[current + 1]) {
                peekList.add(current);
            }
        }

        if (peekList.size() <= 2) {
            Deque<Integer> doubleQueue = new ArrayDeque<>();
            doubleQueue.addAll(peekList);
            return doubleQueue;
        }

        int max = peekList.get(0);
        int maxIndexInPeekList = 0;
        for (int index = 1; index < peekList.size(); index++) {
            if (peekList.get(index) > max) {
                max = peekList.get(index);
                maxIndexInPeekList = index;
            }
        }

        Deque<Integer> doubleQueue = new ArrayDeque<>();

        //check 在maxIndex 前后单调递减
        doubleQueue.add(peekList.get(0));
        int currentMax = height[0];
        for (int i = 1; i < maxIndexInPeekList; i++) {
            if (height[peekList.get(i)] > height[peekList.get(i - 1)] && height[peekList.get(i)] >= currentMax) {
                doubleQueue.add(peekList.get(i));
                currentMax = height[peekList.get(i)];
            }
        }

        if (maxIndexInPeekList != 0 && maxIndexInPeekList != peekList.size() - 1) {
            doubleQueue.add(peekList.get(maxIndexInPeekList));
        }

        Deque<Integer> doubleQueueTail = new ArrayDeque<>();
        doubleQueueTail.add(peekList.get(peekList.size() - 1));
        int currentMaxTail = height[peekList.size() - 1];
        for (int i = peekList.size() - 2; i > maxIndexInPeekList; i--) {
            if (height[peekList.get(i)] > height[peekList.get(i + 1)] && height[peekList.get(i)] >= currentMaxTail) {
                doubleQueueTail.addFirst(peekList.get(i));
                currentMaxTail = height[peekList.get(i)];
            }
        }

        doubleQueue.addAll(doubleQueueTail);


        return doubleQueue;
    }

    /**
     * 计算每个区域内接水的容量
     *
     * @param height
     * @param begin
     * @param end
     * @return
     */
    public int capacityInArea(int[] height, int begin, int end) {
        int capacity;
        capacity = Math.min(height[begin], height[end]) * (end - begin - 1);

        for (int index = begin + 1; index < end; index++) {
            capacity -= height[index] * 1;
        }
        return capacity;
    }
}

class Solution42_2 {

    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int capacity = 0;
        //单调递减栈 栈顶的元素不大于栈底的
        Deque<Integer> stack = new ArrayDeque<>();
        int current = 0;

        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = current - stack.peek() - 1;
                int boundedHeight = Math.min(height[current], height[stack.peek()]) - height[top];

                capacity += distance * boundedHeight;
            }
            stack.push(current++);
        }


        return capacity;
    }

}