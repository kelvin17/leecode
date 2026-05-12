package com.blackbear.top150;

import java.util.HashMap;
import java.util.Map;

public class P230_BST {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2, t1, null);

        TreeNode t4 = new TreeNode(4);
        TreeNode t3 = new TreeNode(3, t2, t4);


        TreeNode t7 = new TreeNode(7);
        TreeNode t6 = new TreeNode(6, null, t7);
        TreeNode t5 = new TreeNode(5, t3, t6);


        P230_BST bst = new P230_BST();
        System.out.printf("result : %d\n", bst.kthSmallest(t5, 7));
    }

    class ChildCountInfo {
        int leftChildCount;
        int rightChildCount;

        ChildCountInfo(int leftChildCount, int rightChildCount) {
            this.leftChildCount = leftChildCount;
            this.rightChildCount = rightChildCount;
        }
    }


    public int kthSmallest(TreeNode root, int k) {

        Map<TreeNode, ChildCountInfo> countInfoHashMap = new HashMap<>();

        int total = countOwn(root, countInfoHashMap);
        System.out.println(total);

        for (Map.Entry<TreeNode, ChildCountInfo> entry : countInfoHashMap.entrySet()) {
            System.out.printf("%d, {%d, %d}\n", entry.getKey().val, entry.getValue().leftChildCount,
                    entry.getValue().rightChildCount);
        }

        TreeNode current = root;
        int res = 0;
        int extLeft = 0;

        while (current != null) {
            ChildCountInfo childCountInfo = countInfoHashMap.get(current);
            int totalLeft = extLeft + childCountInfo.leftChildCount;
            if (totalLeft >= k) {
                current = current.left;
            } else {
                if (totalLeft + 1 == k) {
                    //exit
                    res = current.val;
                    break;
                }
                // 往右边移动
                extLeft = totalLeft + 1;
                current = current.right;
            }
        }

        return res;

    }

    public int countOwn(TreeNode node, Map<TreeNode, ChildCountInfo> countInfoHashMap) {
        if (node == null) {
            return 0;
        }

        int left = countOwn(node.left, countInfoHashMap);
        int right = countOwn(node.right, countInfoHashMap);

        countInfoHashMap.put(node, new ChildCountInfo(left, right));

        return left + right + 1;
    }
}
