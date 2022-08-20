package com.blackbear.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P39 {

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,5};
        int target = 8;

        Solution39 solution39 = new Solution39();
        solution39.combinationSum(candidates, target);
    }
}

class Solution39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        //1. 排序 - 为了快速跳过不满足要求的元素
        Arrays.sort(candidates);
        //2. 递归求解
        recursiveSolve(candidates, target, new ArrayList<>(), result);

        return result;
    }

    /**
     * 每一步都是对当前元素的判断和对它子问题的依赖
     *
     * 如果当前元素等于innerTarget：
     *      1、生成一个解，添加到result里
     *      2、不使用用当前元素的情况下，去看是否还有解
     * 如果当前元素小于innerTarget：
     *      1、将当前元素添加到当前解list中，然后再去调用查看加入了该元素之后的子问题，注意，此时候选元素继续包含当前元素（因为一个元素可以被用多次）
     *      2、不适用当前元素的情况下，去看是否还有解
     * 如果当前元素大于innerTarget：
     *      则往前找到第一个不大于innerTarget的元素，看其是否可形成解。对原始元素排序，就是为了此步骤可以快一点
     *
     * @param availabledCandidates 可选元素数组
     * @param innerTarget 当前的target
     * @param possibleList 在到当前元素之前，已经加入到当前解list中的元素
     * @param result       最终结果，当出现了一个解后，需要添加进去，所以一直作为参数传递
     */
    private void recursiveSolve(int[] availabledCandidates, int innerTarget, List<Integer> possibleList, List<List<Integer>> result) {

        if (availabledCandidates == null || availabledCandidates.length == 0) {
            return;
        }

        int currentLength = availabledCandidates.length;
        int currentLast = availabledCandidates[currentLength - 1];

        if (currentLast == innerTarget) {
            //1. 找子解
            if (availabledCandidates.length > 1) {
                List<Integer> tmp = new ArrayList<>();
                tmp.addAll(possibleList);
                recursiveSolve(Arrays.copyOfRange(availabledCandidates, 0, currentLength - 1), innerTarget, tmp, result);
            }

            //2. 添加进去。得到一个解了
            possibleList.add(currentLast);
            result.add(possibleList);
        }
        if (currentLast < innerTarget) {
            //1. 找子解
            if (availabledCandidates.length > 1) {
                List<Integer> tmp = new ArrayList<>();
                tmp.addAll(possibleList);
                recursiveSolve(Arrays.copyOfRange(availabledCandidates, 0, currentLength - 1), innerTarget, tmp, result);
            }

            //2. 添加进去,并缩小target去求解
            possibleList.add(currentLast);
            List<Integer> tmp = new ArrayList<>();
            tmp.addAll(possibleList);
            recursiveSolve(availabledCandidates, innerTarget - currentLast, tmp, result);
        }
        if (currentLast > innerTarget) {
            int index = currentLength - 2;
            while (index >= 0) {
                if (availabledCandidates[index] <= innerTarget) {
                    recursiveSolve(Arrays.copyOfRange(availabledCandidates, 0, index + 1), innerTarget, possibleList, result);
                    break;
                }
                index--;
            }
        }
    }
}
