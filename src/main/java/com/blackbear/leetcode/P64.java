package com.blackbear.leetcode;

public class P64 {
    public static void main(String[] args) {
        Solution64 solution64 = new Solution64();
        int[][] grid = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.printf("min path sum = %d\n", solution64.minPathSum(grid));
    }
}

class Solution64 {
    public int minPathSum(int[][] grid) {

        if (grid == null) {
            return 0;
        }
        int x = grid.length;
        int y = grid[0].length;

        int[][] fmn = new int[x][y];
        fmn[0][0] = grid[0][0];

        for (int i = 1; i < x; i++) {
            fmn[i][0] = fmn[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < y; j++) {
            fmn[0][j] = fmn[0][j - 1] + grid[0][j];
        }

        //i 是行；j是列
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                fmn[i][j] = Math.min(fmn[i - 1][j], fmn[i][j - 1]) + grid[i][j];
            }
        }

        return fmn[x - 1][y - 1];
    }
}
