package com.blackbear.top150;

public class P2_LinkedList {

    public static void main(String[] args) {
        ListNode L17 = new ListNode(9);
        ListNode L16 = new ListNode(9, L17);
        ListNode L15 = new ListNode(9, L16);
        ListNode L14 = new ListNode(9, L15);
        ListNode L13 = new ListNode(9, L14);
        ListNode L12 = new ListNode(9, L13);
        ListNode L11 = new ListNode(9, L12);

        ListNode L24 = new ListNode(9);
        ListNode L23 = new ListNode(9, L24);
        ListNode L22 = new ListNode(9, L23);
        ListNode L21 = new ListNode(9, L22);

        P2_LinkedList list = new P2_LinkedList();
        ListNode result = list.addTwoNumbers(L11, L21);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode currentL1 = l1;
        ListNode currentL2 = l2;
        int add2Next = 0;
        //1. togather
        while (currentL2 != null && currentL1 != null) {
            int val = currentL1.val + currentL2.val + add2Next;
            currentL2.val = val % 10;
            currentL1.val = val % 10;
            add2Next = val / 10;

            if (currentL1.next == null && currentL2.next == null) {
                if (add2Next != 0) {
                    currentL1.next = new ListNode(add2Next);
                }
                return l1;
            }

            currentL1 = currentL1.next;
            currentL2 = currentL2.next;
        }

        boolean isL2 = currentL2 != null;
        processLonger(isL2 ? currentL2 : currentL1, add2Next);

        return isL2 ? l2 : l1;
    }

    private void processLonger(ListNode current, int add2Next) {
        while (current != null) {
            int val = current.val + add2Next;
            current.val = val % 10;
            add2Next = val / 10;

            if (current.next == null && add2Next != 0) {
                current.next = new ListNode(add2Next);
                break;
            }
            current = current.next;
        }

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
