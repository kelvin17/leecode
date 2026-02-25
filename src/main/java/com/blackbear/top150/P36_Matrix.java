package com.blackbear.top150;

import java.util.HashSet;
import java.util.Set;

public class P36_Matrix {

    public static void main(String[] args) {
        char[][] matrix = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        P36_Matrix matrics = new P36_Matrix();
        System.out.printf("The result : %s", matrics.isValidSudoku(matrix));

    }


    public boolean isValidSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            if (!checkInner(board, row, row, 0, 8)) {
                return false;
            }
        }

        for (int col = 0; col < 9; col++) {
            if (!checkInner(board, 0, 8, col, col)) {
                return false;
            }
        }

        for (int rowStart = 0; rowStart <= 6; rowStart = rowStart + 3) {
            for (int colStart = 0; colStart <= 6; colStart = colStart + 3) {
                if (!checkInner(board, rowStart, rowStart + 2, colStart, colStart + 2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkInner(char[][] board, int rowStart, int rowEnd, int colStart, int colEnd) {
        Set<Character> set = new HashSet<>();
        for (int row = rowStart; row <= rowEnd; row++) {
            for (int col = colStart; col <= colEnd; col++) {
                if (board[row][col] != '.') {
                    if (set.contains(board[row][col])) {
                        System.out.printf("Duplicate block: %d, %d, %d, %d\n", rowStart, rowEnd, colStart, colEnd);
                        return false;
                    } else {
                        set.add(board[row][col]);
                    }
                }
            }
        }
        return true;
    }
}
