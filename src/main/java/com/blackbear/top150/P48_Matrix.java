package com.blackbear.top150;

public class P48_Matrix {
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int rowStart = 0; rowStart < Math.floorDiv(length, 2); rowStart++) {
            for (int colStart = rowStart; colStart <= rowStart + (length - 2 * (rowStart + 1)); colStart++) {
                //the start item of every rotation
                int tmp = matrix[rowStart][colStart];
                int fourthRow = length - 1 - colStart;
                int fourthCol = rowStart;
                matrix[rowStart][colStart] = matrix[fourthRow][fourthCol];

                int thirdRow = length - 1 - fourthCol;
                int thirdCol = fourthRow;
                matrix[fourthRow][fourthCol] = matrix[thirdRow][thirdCol];

                int secondRow = length - 1 - thirdCol;
                int secondCol = thirdRow;
                matrix[thirdRow][thirdCol] = matrix[secondRow][secondCol];

                matrix[secondRow][secondCol]= tmp;
            }
        }
    }
}
