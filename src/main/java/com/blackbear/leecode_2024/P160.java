package com.blackbear.leecode_2024;

import java.util.HashSet;
import java.util.Set;

public class P160 {

    static class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }

            Set<ListNode> nodeSet = new HashSet<>();

            ListNode tmp = headA;
            while (tmp != null) {
                nodeSet.add(tmp);
                tmp = tmp.next;
            }

            tmp = headB;
            while (tmp != null) {
                if (nodeSet.contains(tmp)) {
                    return tmp;
                }
                tmp = tmp.next;
            }
            return null;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
