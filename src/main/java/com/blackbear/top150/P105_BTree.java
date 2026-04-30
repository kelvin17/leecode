package com.blackbear.top150;

import java.util.Arrays;

public class P105_BTree {

    public static void main(String[] args) {

        int[] preorder = new int[]{1, 2, 3};
        int[] inorder = new int[]{2, 3, 1};
        P105_BTree tree = new P105_BTree();
        TreeNode root = tree.buildTree(preorder, inorder);
        System.out.println(root.val);
    }

    //avoid to copy the arrays
    public TreeNode buildTree(int[] preorder, int[] inorder, int preBegin, int preEnd, int inBegin, int inEnd) {
        if (preBegin == -1) {
            return null;
        }
        int rootVal = preorder[preBegin];
        TreeNode root = new TreeNode(rootVal);

        int index = inBegin;
        for (; index <= inEnd; index++) {
            if (inorder[index] == rootVal) {
                break;
            }
        }

        int leftPreBegin = -1;
        int leftPreEnd = -1;
        int leftInBegin = -1;
        int leftInEnd = -1;

        int rightPreBegin = -1;
        int rightPreEnd = -1;
        int rightInBegin = -1;
        int rightInEnd = -1;

        //from in; to ex
        if (index > inBegin) {
            leftPreBegin = preBegin + 1;
            leftPreEnd = index;

            leftInBegin = inBegin;
            leftInEnd = index - 1;
        }

        if (index < inEnd) {
            rightPreBegin = index - inBegin + preBegin + 1;
            rightPreEnd = preEnd;

            rightInBegin = index + 1;
            rightInEnd = inEnd;
        }

        root.left = buildTree(preorder, inorder, leftPreBegin, leftPreEnd, leftInBegin, leftInEnd);
        root.right = buildTree(preorder, inorder, rightPreBegin, rightPreEnd, rightInBegin, rightInEnd);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    //space complexity is higher, but the logic is much easier to understand
    public TreeNode buildTree2(int[] preorder, int[] inorder) {

        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);

        int[] leftPre = null;
        int[] rightPre = null;

        int[] leftIn = null;
        int[] rightIn = null;

        int index = 0;
        for (; index < inorder.length; index++) {
            if (inorder[index] == rootVal) {
                break;
            }
        }

        //from in; to ex
        if (index > 0) {
            leftPre = Arrays.copyOfRange(preorder, 1, index + 1);
            leftIn = Arrays.copyOfRange(inorder, 0, index);
        }

        if (index + 1 < inorder.length) {
            rightPre = Arrays.copyOfRange(preorder, index + 1, inorder.length);
            rightIn = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        }

        root.left = buildTree(leftPre, leftIn);
        root.right = buildTree(rightPre, rightIn);

        return root;
    }
}

class TreeNode {
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