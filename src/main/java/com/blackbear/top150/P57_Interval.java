package com.blackbear.top150;

import java.util.ArrayList;
import java.util.List;

public class P57_Interval {
    public static void main(String[] args) {
        int[][] original = new int[][]{
                {3, 5}, {12, 15}
        };

        int[] newInterval = new int[]{6, 6};

        P57_Interval p57_interval = new P57_Interval();
        int[][] result = p57_interval.insert2(original, newInterval);
        for (int[] ints : result) {
            System.out.println(ints[0] + "," + ints[1]);
        }
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }


        int size = intervals.length;

        List<int[]> result = new ArrayList<>();

        int i = 0;
        //1. before newInterval
        while (i < size && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        int[] merged = newInterval;
        while (i < size && merged[1] >= intervals[i][0]) {
            merged[0] = Math.min(merged[0], intervals[i][0]);
            merged[1] = Math.max(merged[1], intervals[i][1]);
            i++;
        }

        result.add(merged);
        //3. before merge
        while (i < size) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }


    public int[][] insert(int[][] intervals, int[] newInterval) {

        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }

        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        int size = intervals.length;

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int iEnd = intervals[i][1];

            if (newStart <= iEnd) {
                //find
                int iStart = intervals[i][0];

                if (newEnd <= iEnd) {
                    //end-2
                    if (newEnd < iStart) {
                        result.add(newInterval);
                        result.add(intervals[i]);
                    } else {
                        //merge
                        int start = Math.min(newStart, iStart);
                        int[] newItem = new int[]{start, iEnd};
                        result.add(newItem);
                    }

                    for (int j = i + 1; j < size; j++) {
                        result.add(intervals[j]);
                    }
                    return result.toArray(new int[result.size()][]);
                } else {
                    if (i == size - 1) {
                        int[] newItem = new int[]{Math.min(newStart, iStart), newEnd};
                        result.add(newItem);
                        return result.toArray(new int[result.size()][]);
                    } else {
                        for (int j = i + 1; j < size; j++) {
                            int jEnd = intervals[j][1];
                            if (newEnd <= jEnd) {
                                //merge
                                int jStart = intervals[j][0];

                                int start = Math.min(newStart, iStart);
                                if (newEnd >= jStart) {
                                    //merge 到 jEnd
                                    int[] newOne = new int[]{start, jEnd};
                                    result.add(newOne);
                                } else {
                                    //merge to newEnd. keep interval j
                                    int[] newOne = new int[]{start, newEnd};

                                    result.add(newOne);
                                    result.add(intervals[j]);
                                }

                                for (int k = j + 1; k < size; k++) {
                                    result.add(intervals[k]);
                                }
                                return result.toArray(new int[result.size()][]);
                            } else {
                                if (j == size - 1) {
                                    //结束了...
                                    int start = Math.min(newStart, iStart);
                                    int[] last = new int[]{start, newEnd};
                                    result.add(last);
                                    return result.toArray(new int[result.size()][]);
                                }
                            }
                        }

                    }


                }
                break;
            } else {
                result.add(intervals[i]);
                if (i == size - 1) {
                    //end-1
                    result.add(newInterval);
                }
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
