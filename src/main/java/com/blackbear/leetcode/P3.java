package com.blackbear.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P3 {
    public static void main(String[] args) {

        Solution3_2 solution3 = new Solution3_2();
        String s = "abba";
        System.out.println(solution3.lengthOfLongestSubstring(s));
    }


}

class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int totalLength = s.length();

        for (int index = 0; index < totalLength; index++) {

            int maxSubLength = getCurrentBeginMaxLength(s.substring(index));

            if (result < maxSubLength) {
                result = maxSubLength;
            }

            if (result > totalLength - 1 - index) {
                //可以提前退出了
                break;
            }

        }

        System.out.println("maxSubLength:" + result);
        return result;
    }

    private int getCurrentBeginMaxLength(String substring) {

        Set<String> diffStringSet = new HashSet<String>();

        for (int index = 0; index < substring.length(); index++) {
            int preSetSize = diffStringSet.size();
            diffStringSet.add(substring.substring(index, index + 1));
            int currSetSize = diffStringSet.size();

            if (currSetSize == preSetSize) {
                return currSetSize;
            }
        }

        return substring.length();
    }
}

class Solution3_2 {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int windowBegin = 0;
        int maxLength = 0;

        Map<Character, Integer> charAndItsIndex = new HashMap<>();
        for (int index = 0; index < s.length(); index++) {
            if (charAndItsIndex.containsKey(s.charAt(index)) && windowBegin <= charAndItsIndex.get(s.charAt(index))) {
                windowBegin = charAndItsIndex.get(s.charAt(index)) + 1;
            }
            charAndItsIndex.put(s.charAt(index), index);
            maxLength = Math.max(maxLength, index - windowBegin + 1);
        }

        return maxLength;
    }

}


