package com.blackbear.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class P6 {

    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        System.out.println("Result:" + solution6.convert("PAYPALISHIRING", 4));
    }
}


class Solution6 {
    public String convert(String s, int numRows) {
        char[] sarray = s.toCharArray();
        List<StringBuilder> rowList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rowList.add(new StringBuilder());
        }

        for (int index = 0; index < s.length(); index++) {
            int row;
            if ((index / (numRows - 1)) % 2 == 0) {
                row = index % (numRows - 1);
            } else {
                row = numRows - 1 - index % (numRows - 1);
            }
            rowList.get(row).append(sarray[index]);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            result.append(rowList.get(i).toString());
        }

        return result.toString();
    }
}
