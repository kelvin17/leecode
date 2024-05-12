package com.blackbear.basealg;

import org.springframework.boot.logging.java.JavaLoggingSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortAlg {

    public static void main(String[] args) {
        SortAlg sortAlg = new SortAlg();

//        int[] nums = {5, 45, 5, 7, 2, 9, 4};
        int[] nums = {1, 5, 2, 6, 3, 7, 3};
        sortAlg.printArr(nums);
//        sortAlg.insertSort(nums, false);
//        sortAlg.bubbleSort(nums, false);
        sortAlg.quickSortInPlace(nums, 0, nums.length - 1);
        sortAlg.quickSort(nums);

        sortAlg.printArr(nums);
    }

    public void printArr(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    private void swap(int[] nums, int aIndex, int bIndex) {
        int tmp = nums[aIndex];
        nums[aIndex] = nums[bIndex];
        nums[bIndex] = tmp;
    }

    public void selectSort(int[] nums, boolean isNormalOrder) {
        //时间复杂度 O(n^2);空间复杂度O(1)
        //isNormalOrder 从小到大
        //1. find the 最小的，与当前位置交换
        for (int current = 0; current < nums.length - 1; current++) {
            //最后一位不用看了
            int index = current;
            for (int i = current + 1; i < nums.length; i++) {
                if (isNormalOrder) {
                    if (nums[index] > nums[i]) {
                        index = i;
                    }
                } else {
                    if (nums[index] < nums[i]) {
                        index = i;
                    }
                }
            }
            if (index != current) {
                swap(nums, index, current);
            }
        }
    }


    public void bubbleSort(int[] nums, boolean isNormalOrder) {
        //时间复杂度 O(n^2);空间复杂度O(1)
        for (int j = 0; j < nums.length; j++) {
            for (int i = 0; i < nums.length - 1 - j; i++) {
                if (isNormalOrder) {
                    if (nums[i] > nums[i + 1]) {
                        swap(nums, i, i + 1);
                    }
                } else {
                    if (nums[i] < nums[i + 1]) {
                        swap(nums, i, i + 1);
                    }
                }
            }
        }
    }

    public void insertSort(int[] nums, boolean isNormalOrder) {
        //1. 时间复杂度小于O(n^2)
        //思想：认为前n个已经是有序的。新来的这个找到不小于前面，大于后面的位置即可。
        for (int i = 1; i < nums.length; i++) {
            //1. 第一个不用排，从第二个开始 - i表示两个含义；第一：前面i个元素已经有序了；i是当前这个元素的index

            //2. 逐次与前面一个比较。如果比前面的小，就交换。否则就结束了。
            for (int j = i; j > 0; j--) {
                if (isNormalOrder) {
                    if (nums[j] < nums[j - 1]) {
                        swap(nums, j, j - 1);
                    } else {
                        //前面已经都有序了
                        break;
                    }
                } else {
                    if (nums[j] > nums[j - 1]) {
                        swap(nums, j, j - 1);
                    } else {
                        //前面已经都有序了
                        break;
                    }
                }
            }
        }
    }

    public void quickSort(int[] nums) {

        if (nums == null) {
            return;
        }

        List<Integer> result = quickSortInner(Arrays.stream(nums).boxed().collect(Collectors.toList()));

        for (int i = 0; i < result.size(); i++) {
            nums[i] = result.get(i);
        }
    }

    public void quickSortInPlace(int[] nums, int beginIndex, int endIndex) {
        //要排序的部分是从从nums[beginIndex]到nums[endIndex]
        if (beginIndex >= endIndex) {
            return;
        }

        //1. 分区
        int left = beginIndex;
        int right = endIndex;
        int baseNum = nums[left];
        while (right > left) {
            //1. 此刻left指针指向一个空闲位置，可以用来放比base小的数据。所以先从右往左找小于baseNum的数
            while (right > left) {
                if (nums[right] < baseNum) {
                    //find
                    nums[left] = nums[right];
                    left++;
                    break;
                } else {
                    right--;
                }
            }

            //2.此刻right指针指向一个空闲位置，可以用来放比base大的数据。所以先从右往左找大于baseNum的数
            while (right > left) {
                if (nums[left] > baseNum) {
                    //find
                    nums[right] = nums[left];
                    right--;
                    break;
                } else {
                    left++;
                }
            }
        }

        int basePlace = left;
        nums[basePlace] = baseNum;

        //2. 对两个子序列做快排
        quickSortInPlace(nums, beginIndex, basePlace - 1);
        quickSortInPlace(nums, basePlace + 1, endIndex);

    }

    public List<Integer> quickSortInner(List<Integer> nums) {
        if (nums == null || nums.size() == 1) {
            return nums;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int index = 1; index < nums.size(); index++) {
            if (nums.get(index) <= nums.get(0)) {
                left.add(nums.get(index));
            } else {
                right.add(nums.get(index));
            }
        }

        if (left.size() > 1) {
            left = quickSortInner(left);
        }

        if (right.size() > 1) {
            right = quickSortInner(right);
        }

        left.add(nums.get(0));
        left.addAll(right);

        return left;
    }
}
