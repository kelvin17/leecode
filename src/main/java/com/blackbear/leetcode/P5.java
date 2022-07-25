package com.blackbear.leetcode;

public class P5 {

    public static void main(String[] args) {
        String s = "cbbd";
        Solution5 solution5 = new Solution5();
        System.out.println(solution5.longestPalindrome(s));
    }
}

class Solution5 {
    public String longestPalindrome(String s) {
        String result = "";
        int maxLen = 0;
        char[] strArray = s.toCharArray();

        for (int begin = 0; begin < strArray.length; begin++) {
            for (int end = strArray.length - 1; end >= begin; end--) {
                if (isPalindromeStr(strArray, begin, end)) {
                    int tmpMaxLen = end - begin + 1;
                    if (tmpMaxLen > maxLen) {
                        maxLen = tmpMaxLen;
                        result = s.substring(begin, end + 1);
                    }
                }

            }
        }

        return result;

    }

    private boolean isPalindromeStr(char[] strArray, int begin, int end) {
        while (begin <= end) {
            if (strArray[begin] == strArray[end]) {
                begin++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}
