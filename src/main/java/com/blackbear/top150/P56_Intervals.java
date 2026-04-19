package com.blackbear.top150;

import java.util.*;

public class P56_Intervals {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {4, 7},
                {1, 4}
        };

        P56_Intervals p = new P56_Intervals();
        int[][] result = p.merge(intervals);
        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
    }


    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> resultList = new ArrayList<>();

        for (int i = 0; i < intervals.length; ) {
            int[] mergedInterval = intervals[i];
            int j = i + 1;
            for (; j < intervals.length; j++) {
                int[] checkInterval = intervals[j];
                if (checkInterval[0] <= mergedInterval[1]) {
                    mergedInterval[1] = Math.max(mergedInterval[1], checkInterval[1]);
                } else {
                    break;
                }
            }
            resultList.add(mergedInterval);
            i = j;
        }

        int size = resultList.size();
        return resultList.toArray(new int[size][2]);
    }
}
