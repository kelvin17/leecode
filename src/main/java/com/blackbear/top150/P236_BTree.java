package com.blackbear.top150;

import java.util.HashMap;
import java.util.Map;

public class P236_BTree {

    public static void main(String[] args) {
//        TreeNode t6 = new TreeNode(6);
//
//        TreeNode t7 = new TreeNode(7);
//        TreeNode t4 =  new TreeNode(4);
//        TreeNode t2 = new TreeNode(2);
//        t2.left = t7;
//        t2.right = t4;
//
//        TreeNode t5 = new TreeNode(5);
//        t5.left = t6;
//        t5.right = t2;
//
//        TreeNode t0 = new TreeNode(0);
//        TreeNode t8 = new TreeNode(8);
//        TreeNode t1 = new TreeNode(1);
//        t1.left = t0;
//        t1.right = t8;
//
//        TreeNode t3 = new TreeNode(3);
//        t3.left = t5;
//        t3.right = t1;

        TreeNode t8 = new TreeNode(8);
        TreeNode t_2 = new TreeNode(-2);
        t_2.left = t8;

        TreeNode t4 = new TreeNode(4);
        TreeNode t0 = new TreeNode(0);
        t0.left = t_2;
        t0.right = t4;

        TreeNode t3 = new TreeNode(3);
        TreeNode t_1 = new TreeNode(-1);

        t_1.left = t0;
        t_1.right = t3;

        P236_BTree tree = new P236_BTree();
        TreeNode lcn = tree.lowestCommonAncestor(t_1, t8, t0);
        System.out.println(lcn.val);
    }

    class Info {
        TreeNode parent;
        int level;

        Info(TreeNode parent, int level) {
            this.parent = parent;
            this.level = level;
        }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, Info> helper = new HashMap<>();
        int currLevel = 0;
        //1. BFS and build assisstant info map
        doDFS(root, root, currLevel, p, q, helper);

        //2.
        int levelP = helper.get(p.val).level;
        int levelQ = helper.get(q.val).level;

        //case1
        if (levelP == levelQ) {
            TreeNode pAncestor = helper.get(p.val).parent;
            TreeNode qAncestor = helper.get(q.val).parent;
            while (pAncestor != qAncestor) {
                pAncestor = helper.get(pAncestor.val).parent;
                qAncestor = helper.get(qAncestor.val).parent;
            }
            return pAncestor;
        }

        //case2
        if (levelP < levelQ) {

            //1. first from Q level to p level
            TreeNode qAncestor = helper.get(q.val).parent;
            int levelQAncestor = helper.get(qAncestor.val).level;
            while (levelQAncestor > levelP) {
                qAncestor = helper.get(qAncestor.val).parent;
                levelQAncestor = helper.get(qAncestor.val).level;
            }

            TreeNode pAncestor = p;
            while (pAncestor != qAncestor) {
                pAncestor = helper.get(pAncestor.val).parent;
                qAncestor = helper.get(qAncestor.val).parent;
            }

            return pAncestor;
        }

        //case3-levelP > levelQ
        TreeNode pAncestor = helper.get(p.val).parent;
        int levelPAncestor = helper.get(pAncestor.val).level;
        while (levelPAncestor > levelQ) {
            pAncestor = helper.get(pAncestor.val).parent;
            levelPAncestor = helper.get(pAncestor.val).level;
        }

        TreeNode qAncestor = q;
        while (pAncestor != qAncestor) {
            pAncestor = helper.get(pAncestor.val).parent;
            qAncestor = helper.get(qAncestor.val).parent;
        }

        return pAncestor;
    }

    private void doDFS(TreeNode curr, TreeNode parent, int currLevel,
                       TreeNode p, TreeNode q, Map<Integer, Info> helper) {
        helper.put(curr.val, new Info(parent, currLevel));
        if (curr.left != null) {
            doDFS(curr.left, curr, currLevel + 1, p, q, helper);
        }

        if (curr.right != null) {
            doDFS(curr.right, curr, currLevel + 1, p, q, helper);
        }
    }

}
