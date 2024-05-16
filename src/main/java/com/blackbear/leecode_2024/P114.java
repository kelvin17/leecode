package com.blackbear.leecode_2024;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P114 {
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
        public void flatten(TreeNode root) {

            Deque<TreeNode> helper = new ArrayDeque<>();

            doBianli(root, helper);

            //todo 依次处理helper里的每个元素 - 从尾部拿
            TreeNode child = null;
            while (!helper.isEmpty()) {
                if (child == null) {
                    child = helper.pollLast();
                    child.left = null;
                    child.right = null;
                    continue;
                }

                TreeNode cur = helper.pollLast();
                cur.left = null;
                cur.right = child;

                child = cur;
            }
        }

        private void doBianli(TreeNode root, Deque<TreeNode> helper) {
            if (root == null) {
                return;
            }

            helper.add(root);
            doBianli(root.left, helper);
            doBianli(root.right, helper);
        }
    }
}
