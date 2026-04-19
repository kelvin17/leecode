package com.blackbear.top150;

import java.util.*;

public class P452_Intervals {

    public static void main(String[] args) {

        int[][] intervals = new int[][]{
                {3, 9}, {7, 12}, {3, 8}, {6, 8}, {9, 10}, {2, 9}, {0, 9}, {3, 9}, {0, 6}, {2, 8}
        };

        P452_Intervals solution = new P452_Intervals();
        int result = solution.findMinArrowShots(intervals);
        System.out.println(result);

    }

    public int findMinArrowShots(int[][] points) {

        if (points == null || points.length == 0) {
            return 0;
        }
        if (points.length == 1) {
            return 1;
        }

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int[] current = points[0];
        for (int i = 1; i < points.length; i++) {
            int[] test = points[i];

            if (test[0] <= current[1]) {
                continue;
            }
            arrows++;
            current = test;
        }

        return arrows;
    }
}
