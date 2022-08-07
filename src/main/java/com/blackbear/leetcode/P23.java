package com.blackbear.leetcode;

import lombok.val;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Function;

public class P23 {

//    public static void main(String[] args) {
//        ListNode23 item1_3 = new ListNode23(5);
//        ListNode23 item1_2 = new ListNode23(4, item1_3);
//        ListNode23 item1_1 = new ListNode23(1, item1_2);
//
//        ListNode23 item2_3 = new ListNode23(4);
//        ListNode23 item2_2 = new ListNode23(3, item2_3);
//        ListNode23 item2_1 = new ListNode23(1, item2_2);
//
//        ListNode23 item3_2 = new ListNode23(6);
//        ListNode23 item3_1 = new ListNode23(2, item3_2);
//
//        ListNode23[] arrayList = new ListNode23[]{item1_1, item2_1, item3_1};
//        Solution23 solution23 = new Solution23();
//        solution23.mergeKLists(arrayList);
//    }

    public static void main(String[] args) {
        Comparator<ListNode23> valSorter = new Comparator<ListNode23>() {
            @Override
            public int compare(ListNode23 o1, ListNode23 o2) {
                return o2.val - o1.val;
            }
        };

        PriorityQueue<ListNode23> priorityQueue = new PriorityQueue<ListNode23>(valSorter);

        ListNode23 listNode1 = new ListNode23(1);
        priorityQueue.add(listNode1);
        ListNode23 listNode2 = new ListNode23(2);
        priorityQueue.add(listNode2);
        ListNode23 listNode3 = new ListNode23(3);
        priorityQueue.add(listNode3);
        ListNode23 listNode4 = new ListNode23(4);
        priorityQueue.add(listNode4);
        ListNode23 listNode5 = new ListNode23(5);
        priorityQueue.add(listNode5);

        ListNode23 node = priorityQueue.poll();
        while (node != null) {
            System.out.println(node.val);
            node = priorityQueue.poll();
        }
    }

}


class ListNode23 {
    public int getVal() {
        return val;
    }

    int val;
    ListNode23 next;

    ListNode23() {
    }

    ListNode23(int val) {
        this.val = val;
    }

    ListNode23(int val, ListNode23 next) {
        this.val = val;
        this.next = next;
    }
}

class Solution23 {
    public ListNode23 mergeKLists(ListNode23[] lists) {

        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode23 head = null;
        ListNode23 first = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode23 second = lists[i];
            head = merge2Lists(first, second);
            first = head;
        }
        return head;
    }

    private ListNode23 merge2Lists(ListNode23 first, ListNode23 second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        ListNode23 head = new ListNode23(0);//哨兵，最后返回它的next即可
        ListNode23 tmpTail = head;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                tmpTail.next = first;
                first = first.next;
            } else {
                tmpTail.next = second;
                second = second.next;
            }
            tmpTail = tmpTail.next;
        }

        if (first != null) {
            tmpTail.next = first;
        }

        if (second != null) {
            tmpTail.next = second;
        }

        return head.next;
    }
}