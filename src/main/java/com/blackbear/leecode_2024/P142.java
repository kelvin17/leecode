package com.blackbear.leecode_2024;

import java.util.*;

public class P142 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static class Solution {
        public ListNode detectCycle(ListNode head) {

            if (head == null || head.next == null) {
                return null;
            }

            Set<ListNode> node2Index = new HashSet<>();
            ListNode current = head;

            while (current != null) {
                if (node2Index.contains(current)) {
                    return current;
                }
                node2Index.add(current);
                current = current.next;
            }
            return null;
        }

        public ListNode detectCycle2(ListNode head) {

            if (head == null || head.next == null) {
                return null;
            }

            ListNode fast = head;
            ListNode slow = head;

            while (slow != null) {
                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    //相遇
                    ListNode ptr = head;
                    while (ptr != null) {
                        if (ptr == slow) {
                            return ptr;
                        }
                        ptr = ptr.next;
                        slow = slow.next;
                    }
                    //上面一定会跳出。否则就异常了
                    throw new RuntimeException("不会出现");
                }
            }

            return null;
        }
    }
}
