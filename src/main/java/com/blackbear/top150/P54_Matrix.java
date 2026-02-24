package com.blackbear.top150;

import java.util.ArrayList;
import java.util.List;

public class P54_Matrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        int[][] matrix3 = {
                {3},
                {2}
        };

        int[][] matrix4 = {
                {2, 3}
        };
        P54_Matrix p54_Matrix = new P54_Matrix();
        List<Integer> result = p54_Matrix.spiralOrder(matrix4);
        for (Integer i : result) {
            System.out.printf("{%d},", i);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;

        do {
            System.out.printf("[%d,%d,%d,%d]\n", rowStart, rowEnd, colStart, colEnd);
            putIntoResult(matrix, rowStart, rowStart, colStart, colEnd, result);
            if (rowStart < rowEnd) {
                putIntoResult(matrix, rowStart + 1, rowEnd, colEnd, colEnd, result);
            }
            if (colStart < colEnd && rowStart != rowEnd) {
                putIntoResult(matrix, rowEnd, rowEnd, colEnd - 1, colStart, result);
            }
            if (rowStart + 1 < rowEnd) {
                putIntoResult(matrix, rowEnd - 1, rowStart + 1, colStart, colStart, result);
            }
            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        } while (rowEnd - rowStart >= 1 && colEnd - colStart >= 1);

        if (rowEnd == rowStart) {
            for (int i = colStart; i <= colEnd; i++) {
                result.add(matrix[rowStart][i]);
            }
        } else if (colEnd == colStart) {
            for (int i = rowStart; i <= rowEnd; i++) {
                result.add(matrix[i][colEnd]);
            }
        }

        return result;
    }

    public void putIntoResult(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd,
                              List<Integer> result) {
        if (rowStart == rowEnd) {
            if (colStart <= colEnd) {
                for (int col = colStart; col <= colEnd; col++) {
                    result.add(matrix[rowStart][col]);
                }
            } else {
                for (int col = colStart; col >= colEnd; col--) {
                    result.add(matrix[rowStart][col]);
                }
            }
        } else if (colEnd == colStart) {
            if (rowStart <= rowEnd) {
                for (int row = rowStart; row <= rowEnd; row++) {
                    result.add(matrix[row][colEnd]);
                }
            } else {
                for (int row = rowStart; row >= rowEnd; row--) {
                    result.add(matrix[row][colStart]);
                }
            }
        } else {
            throw new RuntimeException("Logic Error");
        }

    }

}
