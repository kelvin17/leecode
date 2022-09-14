package com.blackbear.leetcode;

public class P48 {

    public static void main(String[] args) {
        //[[1,2,3],[4,5,6],[7,8,9]]
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        Solution48 solution48 = new Solution48();
        solution48.rotate(matrix);
    }
}


class Solution48 {
    public void rotate(int[][] matrix) {

        int rows = matrix.length;
        if (rows <= 1) {
            //1个元素不用转
            return;
        }

        int first;
        int maxIndex = rows - 1;

        for (int i = 0; i < rows / 2; i++) {
            for (int j = i; j < maxIndex - i; j++) {
                first = matrix[i][j];
                matrix[i][j] = matrix[maxIndex - i][j];
                matrix[maxIndex - i][j] = matrix[maxIndex - i][maxIndex - j];
                matrix[maxIndex - i][maxIndex - j] = matrix[i][maxIndex - j];
                matrix[i][maxIndex - j] = first;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }

    }
}
