package com.blackbear.leetcode;

import java.util.*;

public class P32 {

    public static void main(String[] args) {
        String s = "(()";
        Solution32 solution32 = new Solution32();
        System.out.println(solution32.longestValidParentheses(s));
    }
}

class Solution32 {
    /**
     * 参考的解法，最有
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {

        int maxNum = 0;
        if (s.length() <= 1) {
            return 0;
        }

        final char LEFT_BRACKET = '(';


        Deque<Integer> helpStack = new ArrayDeque<Integer>();

        int currentIndex = 0;
        char[] sChars = s.toCharArray();

        helpStack.push(-1); //栈底是 最后一个没有匹配的右括号的下标
        while (currentIndex < s.length()) {

            if (sChars[currentIndex] == LEFT_BRACKET) {
                helpStack.push(currentIndex);
            } else {
                //如果是右括号
                helpStack.pop();
                if (helpStack.isEmpty()) {
                    //把栈底也清掉了。并且此时是右括号。说明它是最后一个没有匹配的右括号
                    helpStack.push(currentIndex);
                } else {
                    maxNum = Math.max(maxNum, currentIndex - helpStack.peek());
                }
            }
            currentIndex += 1;
        }
        return maxNum;
    }
}
