package com.blackbear.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class P11 {

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        Solution11 solution11 = new Solution11();
        System.out.println("MaxArea : " + solution11.maxAreaMultiThread(height));
    }


}

class Solution11 {
    public int maxArea(int[] height) {

        int maxArea = 0;

        //超时 - 时间复杂度n^2
//        for (int begin = 0; begin < height.length - 1; begin++) {
//            for (int end = begin; end <= height.length - 1; end++) {
//                int x = end - begin;
//                int y = Math.min(height[begin], height[end]);
//                if (x * y > maxArea) {
//                    maxArea = x * y;
//                }
//
//            }
//        }

        int begin = 0;
        int end = height.length - 1;

        while (begin <= end - 1) {
            int x = end - begin;
            int y = Math.min(height[begin], height[end]);
            if (x * y > maxArea) {
                maxArea = x * y;
            }

            if (height[begin] > height[end]) {
                end--;
            } else {
                begin++;
            }

        }

        return maxArea;

    }

    public int maxAreaMultiThread(int[] height) {

        int maxArea = 0;

        //超时 - 时间复杂度n^2
        Map<Integer, FutureTask<Integer>> taskMap = new HashMap<>();
        for (int begin = 0; begin < height.length - 1; begin++) {
            final int begin_tmp = begin;

            FutureTask<Integer> everyBeginMax = new FutureTask<>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int innerMaxArea = 0;
                    for (int end = begin_tmp; end <= height.length - 1; end++) {
                        int x = end - begin_tmp;
                        int y = Math.min(height[begin_tmp], height[end]);
                        if (x * y > innerMaxArea) {
                            innerMaxArea = x * y;
                        }
                    }
                    return innerMaxArea;
                }
            });
            new Thread(everyBeginMax).start();
            taskMap.put(begin_tmp, everyBeginMax);
        }

        for (FutureTask<Integer> task : taskMap.values()) {
            try {
                Integer tmpMax = task.get();
                maxArea = Math.max(maxArea, tmpMax);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return maxArea;

    }


}
