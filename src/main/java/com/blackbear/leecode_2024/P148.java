package com.blackbear.leecode_2024;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

public class P148 {

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        heap.add(5);
        heap.add(51);
        heap.add(25);
        heap.add(3);
        heap.add(1);
        heap.add(5);

        while (!heap.isEmpty()){
            System.out.println(heap.poll());
        }
    }

    static class Solution {
        public ListNode sortList(ListNode head) {

            if (head == null || head.next == null) {
                return head;
            }

            //1. 元素放到优先级队列中-大顶堆
            PriorityQueue<ListNode> bigTopHeap = new PriorityQueue<>(Comparator.comparingInt(value -> -value.val));
            ListNode tmp = head;
            while (tmp != null) {
                bigTopHeap.add(tmp);
                tmp = tmp.next;
            }

            ListNode result = new ListNode();
            //2. 每次出队堆顶元素-当前最大，设置next为结果链表即可
            while (!bigTopHeap.isEmpty()) {
                ListNode current = bigTopHeap.poll();
                current.next = result.next;
                result.next = current;
            }

            return result.next;
        }
    }

    static class ListNode {
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
}
