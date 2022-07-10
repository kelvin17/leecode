package com.blackbear.leetcode;

public class P2 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode listNode1 = buildNodeList(String.valueOf(9999999));
        ListNode listNode2 = buildNodeList(String.valueOf(9999));
        solution.addTwoNumbers(listNode1, listNode2);
    }

    private static ListNode buildNodeList(String numString) {

        ListNode result = null;
        ListNode nextNode = null;
        for (int index = 0; index < numString.length(); index++) {
            ListNode currentNode = null;
            int val = Integer.valueOf(numString.substring(index, index + 1));
            if (index == 0) {
                currentNode = new ListNode(val);
            } else {
                currentNode = new ListNode(val, nextNode);
            }
            nextNode = currentNode;

            if (index == numString.length() - 1) {
                result = currentNode;
            }
        }


        return result;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {

    //注意，不要忘记最后一个进位的处理
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode currentL1 = l1;
        ListNode currentL2 = l2;

        int carry = 0;
        ListNode preL1 = null;
        //1 都不为0，进行相加
        while (currentL1 != null && currentL2 != null) {
            int sum = currentL1.val + currentL2.val + carry;
            currentL1.val = sum % 10;
            carry = sum / 10;

            //同时后移
            preL1 = currentL1;
            currentL1 = currentL1.next;
            currentL2 = currentL2.next;
        }

        if (currentL1 == null && currentL2 == null && carry > 0){
            ListNode last = new ListNode(carry);
            preL1.next = last;
        }

        //2 剩下的那个加上进位，然后全部复制过来
        if (currentL1 != null) {
            ListNode tmpPre = null;
            while (currentL1 != null) {
                int sum = currentL1.val + carry;
                currentL1.val = sum % 10;
                carry = sum / 10;

                tmpPre = currentL1;
                currentL1 = currentL1.next;
            }
            if (carry > 0) {
                ListNode last = new ListNode(carry);
                tmpPre.next = last;
            }
        }

        if (currentL2 != null) {
            preL1.next = currentL2;
            ListNode tmpPre = null;
            while (currentL2 != null) {
                int sum = currentL2.val + carry;
                currentL2.val = sum % 10;
                carry = sum / 10;

                tmpPre = currentL2;
                currentL2 = currentL2.next;
            }
            if (carry > 0) {
                ListNode last = new ListNode(carry);
                tmpPre.next = last;
            }
        }

        return l1;
    }


}
