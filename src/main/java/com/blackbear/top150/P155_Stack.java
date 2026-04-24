package com.blackbear.top150;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.TreeMap;

public class P155_Stack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(-2);
        minStack.push(-1);
        minStack.push(-2);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();

    }
}

/**
 * This solution is much better.
 * Keep the min value in every element.
 */
class MinStackNew {

    private List<int[]> elements;
    private int currentTopIndex;

    public MinStackNew() {
        this.elements = new ArrayList<>();
        this.currentTopIndex = -1;
    }

    public void push(int val) {
        if (this.elements.isEmpty()) {
            this.elements.add(new int[]{val, val});
        } else {
            int currentMin = this.elements.get(this.currentTopIndex)[1];
            if (val >= currentMin) {
                this.elements.add(new int[]{val, currentMin});
            } else {
                this.elements.add(new int[]{val, val});
            }
        }
        this.currentTopIndex++;

    }

    public void pop() {
        if (this.elements.isEmpty()) {
            throw new EmptyStackException();
        }

        this.elements.remove(this.currentTopIndex);
        this.currentTopIndex--;
    }

    public int top() {
        if (this.elements.isEmpty()) {
            throw new EmptyStackException();
        }

        return this.elements.get(this.currentTopIndex)[0];
    }

    public int getMin() {
        if (this.elements.isEmpty()) {
            throw new EmptyStackException();
        }

        return this.elements.get(this.currentTopIndex)[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

/**
 * TreeMap: (based on Red and Blank Tree)
 * log(n) -- The height of the tree
 *
 * | 方法               | 时间复杂度        |
 * | ---------------- | ------------ |
 * | `get()`          | **O(log n)** |
 * | `put()`          | **O(log n)** |
 * | `getOrDefault()` | **O(log n)** |
 * | `firstKey()`     | **O(log n)** |
 *
 */
class MinStack {

    private ArrayList<Integer> elements;
    private int currentTopIndex;

    private TreeMap<Integer, Integer> min2Counts;

    public MinStack() {
        this.elements = new ArrayList<>();
        this.min2Counts = new TreeMap<>();
        this.currentTopIndex = -1;
    }

    public void push(int val) {
        this.elements.add(val);
        this.currentTopIndex += 1;

        if (this.min2Counts.isEmpty()) {
            this.min2Counts.put(val, 1);
        } else {
            int count = this.min2Counts.getOrDefault(val, 0);
            this.min2Counts.put(val, count + 1);
        }
    }

    public void pop() {
        if (this.elements.isEmpty()) {
            throw new EmptyStackException();
        }

        int ele = this.elements.remove(this.currentTopIndex);
        this.currentTopIndex -= 1;

        int countOfEle = this.min2Counts.get(ele);
        if (countOfEle == 1) {
            this.min2Counts.remove(ele);
        } else {
            this.min2Counts.put(ele, (countOfEle - 1));
        }
    }

    public int top() {
        if (this.elements.isEmpty()) {
            throw new EmptyStackException();
        }

        return this.elements.get(this.currentTopIndex);
    }

    public int getMin() {
        if (this.elements.isEmpty()) {
            throw new EmptyStackException();
        }

        return this.min2Counts.firstKey();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
