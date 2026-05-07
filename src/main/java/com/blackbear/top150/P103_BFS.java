package com.blackbear.top150;

import java.util.*;

public class P103_BFS {

    public static void main(String[] args) {
        TreeNode t15 = new TreeNode(15);
        TreeNode t7 = new TreeNode(7);
        TreeNode t20 = new TreeNode(20, t15, t7);

        TreeNode t9 = new TreeNode(9);
        TreeNode t3 = new TreeNode(3, t9, t20);

        P103_BFS bfs = new P103_BFS();
        List<List<Integer>> res = bfs.zigzagLevelOrder(t3);
        for (List<Integer> l : res) {
            for (Integer i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();

        Deque<TreeNode> queueL2R = new LinkedList<>();
        queueL2R.offer(root);
        Deque<TreeNode> queueR2L = new LinkedList<>();

        while (!queueL2R.isEmpty() || !queueR2L.isEmpty()) {

            List<Integer> levelRes = new ArrayList<>();

            if (!queueL2R.isEmpty()) {
                //must from left 2 right
                while (!queueL2R.isEmpty()) {
                    TreeNode node = queueL2R.poll();
                    levelRes.add(node.val);
                    if (node.left != null) {
                        queueR2L.offer(node.left);
                    }
                    if (node.right != null) {
                        queueR2L.offer(node.right);
                    }

                }

            } else {
                while (!queueR2L.isEmpty()) {
                    TreeNode node = queueR2L.pollLast();
                    levelRes.add(node.val);
                    if (node.right != null) {
                        queueL2R.addFirst(node.right);
                    }
                    if (node.left != null) {
                        queueL2R.addFirst(node.left);
                    }
                }
            }
            res.add(levelRes);
        }


        return res;
    }

}
