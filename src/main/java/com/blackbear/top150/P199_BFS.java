package com.blackbear.top150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P199_BFS {

    /**
     * do BFS
     * and the result is the last elements in each level.
     * @param args
     */

    public static void main(String[] args) {
        TreeNode t5 = new TreeNode(5);
        TreeNode t4 = new TreeNode(4, t5, null);

        TreeNode t2 = new TreeNode(2, t4, null);

        TreeNode t3 = new TreeNode(3);
        TreeNode t1 = new TreeNode(1, t2, t3);

        P199_BFS p = new P199_BFS();
        List<Integer> res = p.rightSideView(null);
        for (Integer i : res) {
            System.out.println(i);
        }
    }

    public List<Integer> rightSideView(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();

        //1. init
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        Queue<TreeNode> queueTmp = new LinkedList<>();
        //2.
        while (!queue.isEmpty()) {
            TreeNode head = queue.poll();
            if (queue.isEmpty()) {
                res.add(head.val);
            }
            if (head.left != null) {
                queueTmp.offer(head.left);
            }
            if (head.right != null) {
                queueTmp.offer(head.right);
            }
            if (queue.isEmpty() && !queueTmp.isEmpty()) {
                queue = queueTmp;
                queueTmp = new LinkedList<>();
            }
        }

        return res;
    }


}
