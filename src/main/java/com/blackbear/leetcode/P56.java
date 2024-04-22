package com.blackbear.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class P56 {

    public static void main(String[] args) {
        int[][] arg = new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
        Solution56 solution56 = new Solution56();
        int[][] result = solution56.merge(arg);
        System.out.println(result);
    }
}

class Solution56 {
    public int[][] merge(int[][] intervals) {

        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        //key 是start int
        TreeMap<Integer, int[]> integerTreeMap = new TreeMap<>();
        integerTreeMap.put(intervals[0][0], intervals[0]);

        //做检查和合并
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            boolean hasMerged = false;
            for (Map.Entry<Integer, int[]> item : integerTreeMap.entrySet()) {
                if (start >= item.getKey()) {
                    //start >= 本区间的start
                    if (start <= item.getValue()[1]) {
                        if (end >= item.getValue()[1]) {
                            item.getValue()[1] = end;
                        }

                        hasMerged = true;
                        break;
                    }
                } else {
                    //start < 本区间的start
                    if (end >= item.getValue()[0]) {

                        //进行合并，并删除之前的
                        item.getValue()[0] = start;
                        item.getValue()[1] = Math.max(end, item.getValue()[1]);
                        integerTreeMap.put(start, item.getValue());

                        integerTreeMap.remove(item.getKey());

                        hasMerged = true;
                        break;
                    }
                }
            }
            if (!hasMerged) {
                integerTreeMap.put(start, intervals[i]);
            }
        }


        int[][] result = new int[integerTreeMap.size()][2];
        int index = 0;
        for (int[] value : integerTreeMap.values()) {
            result[index] = value;
            index++;
        }

        return result;
    }

}
