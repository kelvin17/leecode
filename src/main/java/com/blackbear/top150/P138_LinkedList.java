package com.blackbear.top150;

import java.util.HashMap;
import java.util.Map;

public class P138_LinkedList {

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        Node newHead = new Node(head.val);

        if (head.next == null) {
            if (head.random != null) {
                newHead.random = newHead;
            }
            return newHead;
        }

        Map<Node, Node> old2New = new HashMap<>();

        Node oldCur = head;
        Node newCur = newHead;
        old2New.put(oldCur, newCur);

        while (oldCur.next != null) {
            Node newNext = new Node(oldCur.next.val);
            newCur.next = newNext;

            oldCur = oldCur.next;
            newCur = newNext;

            old2New.put(oldCur, newCur);
        }

        oldCur = head;
        newCur = newHead;
        while (oldCur != null) {
            if (oldCur.random != null) {
                newCur.random = old2New.get(oldCur.random);
            }

            oldCur = oldCur.next;
            newCur = newCur.next;
        }
        return newHead;
    }
}


class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}