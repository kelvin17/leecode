package com.blackbear.leecode_2024;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P101 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {

        public boolean isSymmetricRecur(TreeNode root) {

            if (root == null) {
                return true;
            }

            return recur(root.left, root.right);
        }

        private boolean recur(TreeNode left, TreeNode right) {

            if (left == null && right == null) {
                return true;
            }

            if ((left == null && right != null) || (left != null && right == null)) {
                return false;
            }

            if (left.val != right.val) {
                return false;
            }

            return recur(left.left, right.right) && recur(left.right, right.left);

        }

        public boolean isSymmetric(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return true;
            }

            TreeNode watchDog = new TreeNode(-200);
            List<TreeNode> leftList = new ArrayList<>();
            List<TreeNode> rightList = new ArrayList<>();
            leftList.add(root.left);
            rightList.add(root.right);

            while (leftList.size() == rightList.size() && leftList.size() > 0) {

                List<TreeNode> nextLeftList = new ArrayList<>();
                List<TreeNode> nextRightList = new ArrayList<>();

                for (int index = 0; index < leftList.size(); index++) {
                    TreeNode left = leftList.get(index);
                    TreeNode right = rightList.get(index);

                    if (left.val != right.val) {
                        return false;
                    }
                    //todo null的处理
                    if (left != watchDog) {
                        nextLeftList.add(left.left == null ? watchDog : left.left);
                        nextLeftList.add(left.right == null ? watchDog : left.right);
                    }

                    if (right != watchDog) {
                        nextRightList.add(right.right == null ? watchDog : right.right);
                        nextRightList.add(right.left == null ? watchDog : right.left);
                    }
                }

                leftList = nextLeftList;
                rightList = nextRightList;
            }

            if (leftList.size() != rightList.size()) {
                return false;
            }

            return true;
        }
    }
}
