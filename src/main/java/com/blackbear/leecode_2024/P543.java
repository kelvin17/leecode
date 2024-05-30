package com.blackbear.leecode_2024;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P543 {

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
        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int zhijing = 0;

            Map<TreeNode, Map<String, Integer>> node2LevelMap = new HashMap<>();

            //1. 第一次遍历，统计每个节点的左右最大深度
            calMaxLevel(root, node2LevelMap);
            //2. 第二次遍历，找出直径
            zhijing = getZhijing(root, node2LevelMap);

            return zhijing;

        }

        private int getZhijing(TreeNode currentNode, Map<TreeNode, Map<String, Integer>> node2LevelMap) {
            //do 遍历
            int current = node2LevelMap.get(currentNode).get("left") + node2LevelMap.get(currentNode).get("right");
            int left = currentNode.left != null ? getZhijing(currentNode.left, node2LevelMap) : 0;
            int right = currentNode.right != null ? getZhijing(currentNode.right, node2LevelMap) : 0;

            return Math.max(Math.max(current, left), right);
        }

        //返回当前节点的最大level。并设置它的左右最大level
        public Integer calMaxLevel(TreeNode node, Map<TreeNode, Map<String, Integer>> node2LevelMap) {
            Map<String, Integer> level = new HashMap<>();
            node2LevelMap.put(node, level);

            Integer left = (node.left == null ? 0 : (1 + calMaxLevel(node.left, node2LevelMap)));
            level.put("left", left);

            Integer right = (node.right == null ? 0 : (1 + calMaxLevel(node.right, node2LevelMap)));
            level.put("right", right);

            return left > right ? left : right;
        }
    }
}
