package com.blackbear.leetcode;

public class P21 {

    public static void main(String[] args) {
        ListNode last1 = new ListNode(4);
        ListNode last2 = new ListNode(2, last1);
        ListNode head1 = new ListNode(1, last2);

        ListNode last21 = new ListNode(4);
        ListNode last22 = new ListNode(3, last21);
        ListNode head2 = new ListNode(1, last22);

        Solution21 solution21 = new Solution21();
        solution21.mergeTwoLists(head1, head2);
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

class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode head = null;
        ListNode tail = null;

        boolean isFirst = true;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                if (isFirst) {
                    head = list1;
                    isFirst = false;
                }else {
                    tail.next = list1;
                }
                tail = list1;
                list1 = list1.next;
            } else {
                if (isFirst) {
                    head = list2;
                    isFirst = false;
                }else {
                    tail.next = list2;
                }
                tail = list2;
                list2 = list2.next;
            }
        }

        tail.next = list1 != null ? list1 : (list2 != null ? list2 : null);

        return head;
    }
}
