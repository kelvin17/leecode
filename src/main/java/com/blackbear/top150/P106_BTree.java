package com.blackbear.top150;

import java.util.Arrays;

public class P106_BTree {

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] preorder = {9, 15, 7, 20, 3};
        P106_BTree tree = new P106_BTree();
        TreeNode root = tree.buildTree(inorder, preorder);
        System.out.println(root.val);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        if (inorder == null || inorder.length == 0) {
            return null;
        }

        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);

        int index = 0;
        for (; index < inorder.length; index++) {
            if (inorder[index] == rootVal) {
                break;
            }
        }

        int[] leftInorder = null;
        int[] leftPostorder = null;

        int[] rightInorder = null;
        int[] rightPostorder = null;

        if (index >= 1) {
            leftInorder = Arrays.copyOfRange(inorder, 0, index);
            leftPostorder = Arrays.copyOfRange(postorder, 0, index);
        }

        if (index < inorder.length - 1) {
            rightInorder = Arrays.copyOfRange(inorder, index + 1, inorder.length);
            rightPostorder = Arrays.copyOfRange(postorder, index, inorder.length - 1);
        }

        root.left = buildTree(leftInorder, leftPostorder);
        root.right = buildTree(rightInorder, rightPostorder);

        return root;
    }
}
