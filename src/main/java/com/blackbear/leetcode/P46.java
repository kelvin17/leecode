package com.blackbear.leetcode;

import java.util.*;

public class P46 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Solution46 solution46 = new Solution46();
        List<List<Integer>> result = solution46.permute(nums);
        System.out.printf(result.toString());
    }

}

class Solution46 {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Set<Integer> itemSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            itemSet.add(nums[i]);
        }

        dfsSolve(itemSet, new ArrayDeque<>(), result);

        return result;
    }

    private void dfsSolve(Set<Integer> itemSet, Deque<Integer> currentList, List<List<Integer>> result) {

        //出口
        if (itemSet.size() == 1) {
            Integer item = itemSet.iterator().next();
            currentList.push(item);
            result.add(currentList.stream().toList());
            currentList.pop();
            return;
        }

        //递归
        for (Integer item : itemSet) {
            currentList.push(item);
            Set<Integer> newSet = new HashSet<>();
            newSet.addAll(itemSet);
            newSet.remove(item);

            dfsSolve(newSet, currentList, result);
            currentList.pop();
        }
    }
}
