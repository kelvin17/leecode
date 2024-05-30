package com.blackbear.leecode_2024;

import java.util.*;

public class P114 {

    public static void main(String[] args) {
        Map<Character, Integer> chat2IntMap = new HashMap<>();
        Map<Integer, Character> int2CharMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            char key = (char) ('0' + i);
            chat2IntMap.put(key, i);
            int2CharMap.put(i, key);
        }

        for (int i = 0; i < 26; i++) {
            char key = (char) ('a' + i);
            chat2IntMap.put(key, i + 10);
            int2CharMap.put(i + 10, key);
        }

        for (Map.Entry<Character, Integer> item : chat2IntMap.entrySet()) {
            System.out.println("key:" + item.getKey() + "-value:" + item.getValue());
        }

        for (Map.Entry<Integer, Character> item : int2CharMap.entrySet()) {
            System.out.println("key:" + item.getKey() + "-value:" + item.getValue());
        }
    }

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
