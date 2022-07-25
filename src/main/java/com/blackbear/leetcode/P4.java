package com.blackbear.leetcode;

public class P4 {

    public static void main(String[] args) {
        int[] nums1 = new int[4];
        nums1[0] = 2;
        nums1[1] = 2;
        nums1[2] = 4;
        nums1[3] = 4;

        int[] nums2 = new int[4];
        nums2[0] = 2;
        nums2[1] = 2;
        nums2[2] = 4;
        nums2[3] = 4;

        Solution42 solution4 = new Solution42();
        solution4.findMedianSortedArrays(nums1, nums2);
    }
}


class Solution4 {
    //利用2个数组有序的说明，使用归并排序的办法，实现m+n的时间复杂度算法
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        double midValue = 0;

        int i = 0, j = 0;

        int[] totalNums = new int[nums1.length + nums2.length];
        //2个数组合并排序
        for (int index = 0; index < totalNums.length; index++) {

            if (i >= nums1.length) {
                totalNums[index] = nums2[j++];
                continue;
            }

            if (j >= nums2.length) {
                totalNums[index] = nums1[i++];
                continue;
            }

            if (nums1[i] < nums2[j]) {
                totalNums[index] = nums1[i++];
            } else {
                totalNums[index] = nums2[j++];
            }
        }

        //获取结果
        if (totalNums.length % 2 == 0) {
            //偶数
            midValue = (totalNums[(totalNums.length / 2) - 1] + totalNums[(totalNums.length / 2)]) / 2.0;
        } else {
            midValue = totalNums[totalNums.length / 2];
        }

        return midValue;
    }
}


class Solution42 {
    /**
     * 思路说明
     * 1. 只有2分查找，才能实现log(m+n)
     * 2. 利用找第k大的数的思路。注意第k大的k是从1起始的。并且可以利用 奇数+1/2 和 (奇数+2)/2 是一样大来把奇偶差别屏蔽掉。问题转化成找2个第k大的数
     * 3. 利用2分查找时，要注意(kth/2 - 1) + (kth/2 - 1) <= kth -2 不是等于的。所以不能去认为他俩相等的时候，就一定有一个是第kth大的数
     *
     * 思路还好，但是对于各种边界的处理还需要再慢慢看
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        double midValue = 0;

        int iBegin = 0, jBegin = 0;
        int totalSize = nums1.length + nums2.length;

        //核心1 - 第几大数据 比 下标值大一。在这里会更容易使用
        int left = (totalSize + 1) / 2;
        int right = (totalSize + 2) / 2;

        //找到第k小的数在nums1或者nums2中的位置
        midValue = (findKth(left, iBegin, jBegin, nums1, nums2) + findKth(right, iBegin, jBegin, nums1, nums2)) / 2.0;

        return midValue;
    }

    /**
     * 第k大的数不是从0开始，是从第1大开始的意思
     *
     * @param kTh
     * @param iBegin
     * @param jBegin
     * @param nums1
     * @param nums2
     * @return
     */
    private double findKth(int kTh, int iBegin, int jBegin, int[] nums1, int[] nums2) {
        if (iBegin >= nums1.length) {
            return nums2[jBegin + kTh - 1];
        }

        if (jBegin >= nums2.length) {
            return nums1[iBegin + kTh - 1];
        }

        if (kTh == 1) {//找第一个数。如果留到下面去了，会导致下标"越界"
            return Math.min(nums1[iBegin], nums2[jBegin]);
        }

        // -1 是因为处理第k大于下标的关系
        int midVal1 = iBegin + kTh / 2 - 1 < nums1.length ? nums1[iBegin + kTh / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = jBegin + kTh / 2 - 1 < nums2.length ? nums2[jBegin + kTh / 2 - 1] : Integer.MAX_VALUE;

        //核心2 - (kth/2 - 1) + (kth/2 - 1) <= kth -2 的。。。所以不能去认为他俩相等的时候，就一定有一个是第kth大的数
        if (midVal1 < midVal2) {
            return findKth(kTh - kTh / 2, iBegin + kTh / 2, jBegin, nums1, nums2);
        } else {
            return findKth(kTh - kTh / 2, iBegin, jBegin + kTh / 2, nums1, nums2);
        }
    }
}
