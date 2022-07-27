package com.blackbear.leetcode;

import java.util.ArrayList;

public class P19 {
}


class ListNode19 {
    int val;
    ListNode19 next;

    ListNode19() {
    }

    ListNode19(int val) {
        this.val = val;
    }

    ListNode19(int val, ListNode19 next) {
        this.val = val;
        this.next = next;
    }
}

class Solution19 {
    public ListNode19 removeNthFromEnd(ListNode19 head, int n) {
        //todo 特殊处理，空串情况
        ListNode19 result = null;
        if (head == null) {
            return null;
        }

        ArrayList<ListNode19> arrayList = new ArrayList<>();
        ListNode19 current = head;
        int length = 0;

        while (current != null) {
            arrayList.add(current);
            length++;
            current = current.next;
        }

        int deleteIndex = length - n;

        if (deleteIndex == 0) {
            if (arrayList.size() > 1) {
                return arrayList.get(1);
            } else {
                return null;
            }
        }

        ListNode19 preNode = arrayList.get(deleteIndex - 1);
        ListNode19 deleteNode = arrayList.get(deleteIndex);
        preNode.next = deleteNode.next;

        result = arrayList.get(0);

        return result;
    }
}
