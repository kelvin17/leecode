package com.blackbear.top150;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class P146_LRU_LinkedList {
}

class Node2 {
    int key;
    int value;
    Node2 next;
    Node2 prev;
}

class LRUCache2 {

    Map<Integer, Node2> map;
    int capacity;
    Node2 head;
    Node2 tail;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.head = new Node2();
        this.tail = new Node2();

        head.next = tail;
        tail.prev = head;
    }

    //put to the head
    private void addNode(Node2 node) {
        Node2 temp = head.next;

        head.next = node;
        node.prev = head;

        node.next = temp;
        temp.prev = node;
    }

    //delete the specific node
    private void deleteNode(Node2 node) {
        Node2 prev = node.prev;
        Node2 next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    public int get(int key) {

        if (map.containsKey(key)) {
            Node2 node = map.get(key);
            int value = node.value;
            deleteNode(node);
            addNode(node);

            return value;
        } else {
            return -1;
        }

    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node2 node = map.get(key);
            node.value = value;

            deleteNode(node);

            addNode(node);
            return;
        }

        if (map.size() == capacity) {
            Node2 evictNode = this.tail.prev;
            deleteNode(evictNode);
            map.remove(evictNode.key);
        }

        Node2 node = new Node2();
        node.key = key;
        node.value = value;
        addNode(node);

        map.put(key, node);


    }
}

class LRUCache {

    Map<Integer, Integer> kvPair;
    int capacity;
    Queue<Integer> usedQueue;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        kvPair = new HashMap<>();
        usedQueue = new LinkedList<>();
    }

    public int get(int key) {
        int value = this.kvPair.getOrDefault(key, -1);
        if (value == -1) {
            return -1;
        }
        //put the key to the tail
        this.usedQueue.remove(key);
        this.usedQueue.add(key);
        return value;
    }

    public void put(int key, int value) {
        if (this.kvPair.containsKey(key)) {
            this.kvPair.put(key, value);

            this.usedQueue.remove(key);
            this.usedQueue.add(key);
            return;
        }

        if (this.capacity == this.kvPair.size()) {
            int evictKey = this.usedQueue.poll();
            this.kvPair.remove(evictKey);
        }
        this.kvPair.put(key, value);
        this.usedQueue.add(key);
    }
}