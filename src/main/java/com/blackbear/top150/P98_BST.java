package com.blackbear.top150;

public class P98_BST {

    public static void main(String[] args) {
        TreeNode t41 = new TreeNode(41);
        TreeNode t43 = new TreeNode(43, t41, null);

        TreeNode t44 = new TreeNode(44, t43, null);

        TreeNode t42 = new TreeNode(42, null, t44);
        TreeNode t45 = new TreeNode(45, t42, null);
        P98_BST bst = new P98_BST();
        if (bst.isValidBST(t45)) {
            System.out.println("It's a BST");
        } else {
            System.out.println("It's not a BST");
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root.left != null) {
            if (maxOfLeft(root.left) >= root.val) {
                return false;
            }
            if (!isValidBST(root.left)) {
                return false;
            }
        }
        if (root.right != null) {
            if (minOfRight(root.right) <= root.val) {
                return false;
            }
            if (!isValidBST(root.right)) {
                return false;
            }
        }
        return true;
    }

    public int maxOfLeft(TreeNode left) {
        int val = left.val;
        TreeNode mostRight = left.right;
        while (mostRight != null) {
            val = mostRight.val;
            mostRight = mostRight.right;
        }
        return val;
    }

    public int minOfRight(TreeNode right) {
        int val = right.val;
        TreeNode mostLeft = right.left;
        while (mostLeft != null) {
            val = mostLeft.val;
            mostLeft = mostLeft.left;
        }
        return val;
    }

    /**
     * This solution is much easier than mine.
     *
     * Each step is to check the range.
     *
     * Other thing is Long.MIN_VALUE is -2^63,Long.MAX_VALUE is 2^63-1
     * Integer.MIN_VALUE is -2^31,Integer.MAX_VALUE is 2^31-1
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean valid(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val > min && root.val < max) {
            return valid(root.left, min, root.val) && valid(root.right, root.val, max);
        }
        return false;
    }
}
