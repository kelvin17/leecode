package com.blackbear.leecode_2024;

import java.util.Random;

public class P1206SkipList {

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        skiplist.add(5);
        skiplist.add(8);
        skiplist.add(12);

        if (skiplist.search(12)) {
            System.out.println("find");
        }
    }

    static class Skiplist {
        int maxLevel = 10;

        class SkipListNode {
            int value;
            SkipListNode[] nextNodes = new SkipListNode[maxLevel];

            public SkipListNode(int value) {
                this.value = value;
            }
        }

        public Skiplist() {

        }

        SkipListNode watchDog = new SkipListNode(-1);
        Random random = new Random();

        private void findEveryLevel(int target, SkipListNode[] ns) {
            //ns 用来存储在每一层的头节点
            SkipListNode cur = watchDog;
            for (int i = maxLevel - 1; i >= 0; i--) {
                while (cur.nextNodes[i] != null && cur.nextNodes[i].value < target) {
                    cur = cur.nextNodes[i];
                }

                ns[i] = cur;
            }
        }

        public boolean search(int target) {

            SkipListNode[] ns = new SkipListNode[maxLevel];
            findEveryLevel(target, ns);

            return ns[0].nextNodes[0] != null && ns[0].nextNodes[0].value == target;
        }

        public void add(int num) {

            SkipListNode[] ns = new SkipListNode[maxLevel];
            findEveryLevel(num, ns);

            SkipListNode newNode = new SkipListNode(num);

            for (int i = 0; i < maxLevel; i++) {
                //每一层都可能要添加
                newNode.nextNodes[i] = ns[i].nextNodes[i];
                ns[i].nextNodes[i] = newNode;
                if (random.nextInt(2) == 0) break;
            }
        }

        public boolean erase(int num) {

            SkipListNode[] ns = new SkipListNode[maxLevel];
            findEveryLevel(num, ns);

            SkipListNode node = ns[0].nextNodes[0];

            //没有
            if (node == null || node.value != num) return false;

            //有，要擦除
            for (int i = 0; i < maxLevel && ns[i].nextNodes[i] == node; i++) {
                ns[i].nextNodes[i] = node.nextNodes[i];
            }
            return true;
        }
    }

}
