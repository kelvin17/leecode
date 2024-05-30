package com.blackbear.leecode_2024;

public class P74 {

    public static void main(String[] args) {
        P74 s = new P74();

        int[][] matrix = {
                {1, 3}
        };
        int target = 3;
        System.out.println("find:" + s.searchMatrix(matrix, target));
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        int hang = matrix.length;
        int lie = matrix[0].length;

        if (hang == 0 || matrix[0][0] > target || matrix[hang - 1][lie - 1] < target) {
            return false;
        }

        boolean found = false;

        //1. 找到可能得行
        int possibleHang = -1;

        int lowHang = 0;
        int highHang = hang - 1;
        while (lowHang <= highHang) {
            int middle = (lowHang + highHang) / 2;
            if (matrix[middle][0] <= target) {
                if (middle + 1 == hang || target < matrix[middle + 1][0]) {
                    //找到
                    possibleHang = middle;
                    break;
                }

                //target 大于middle+1的第一个了。
                lowHang = middle + 1;
            } else {
                highHang = middle;
            }
        }


        //2. 行内检索
        int lowLie = 0;
        int highLie = lie - 1;

        while (lowLie <= highLie) {
            int middle = (highLie + lowLie) / 2;

            if (matrix[possibleHang][middle] == target) {
                return true;
            } else if (matrix[possibleHang][middle] < target) {
                lowLie = middle + 1;
            } else {
                highLie = middle - 1;
            }
        }

        return false;

    }
}
