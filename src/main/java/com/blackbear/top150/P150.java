package com.blackbear.top150;

import java.util.HashSet;
import java.util.Set;

public class P150 {
    public static void main(String[] args) {
        String s = "tmmzuxt";
        P150 p150 = new P150();
        System.out.printf("s:{%s}, result is %d\n", s, p150.lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int left = 0;
        Set<Character> currentSet = new HashSet<>();
        currentSet.add(s.charAt(left));
        int maxLength = 1;

        for (int right = 1; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            if (!currentSet.contains(currentChar)) {
                currentSet.add(currentChar);
                //1. update maxLength
                maxLength = Math.max(maxLength, right - left + 1);
                //2. keep move right to the next
            } else {
                //repeat
                do {
                    if (currentChar == s.charAt(left)) {
                        //find the same one
                        left++;
                        break;
                    } else {
                        currentSet.remove(s.charAt(left));
                        left++;
                    }
                } while (true);
            }
        }
        return maxLength;
    }
}
