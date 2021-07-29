package com.sj.skip;

import java.util.Comparator;

public class SkipList<K,V> {

    private int size;

    private static final int MAX_LEAVER = 32;

    private static final double P = 0.25;

    private final Comparator<K> comparator;

    private final Node<K,V> head;

    // 表示head的层数
    private int level;

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        head = new Node<>();
        head.nexts = new Node[MAX_LEAVER];
    }

    public SkipList() {
        this(null);
    }

    int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable)k1).compareTo((Comparable)k2);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) {

        Node<K,V> node = head;

        for (int i = level -1 ; i >= 0; i--) {

            int comp = -1;

            while (node.nexts[i] != null && (comp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }

            if (comp == 0) {
                return node.nexts[i].value;
            }
        }

        return null;
    }

    public V put(K key, V value) {

        Node<K,V> node = head;
        Node<K,V>[] prevNode = new Node[level];

        for (int i = level -1 ; i >= 0; i--) {

            int comp = -1;

            while (node.nexts[i] != null && (comp = compare(key, node.nexts[i].key)) > 0) {
                //
                node = node.nexts[i];
            }

            if (comp == 0) {
                // 表示节点存在
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            // 第i层的前驱
            prevNode[i] = node;
        }

        // node是前驱节点
        int randomLevel = randomLevel();
        Node<K,V> newNode = new Node<>(key, value, randomLevel);
        for (int i = 0; i < randomLevel; i++) {
            // 当i大于level时
            if (i >= level) {
                head.nexts[i] = newNode;
            }else {
                // prevNode后继赋值给newNode的后继
                newNode.nexts[i] = prevNode[i].nexts[i];
                prevNode[i].nexts[i] = newNode;
            }

        }
        size++;
        level = Math.max(level, randomLevel);
        return value;
    }

    private int randomLevel() {
        int l = 1;
        while (Math.random() < P && l < MAX_LEAVER) {
            l++;
        }
        return l;
    }

    public V remove(K key) {

        Node<K,V> node = head;
        Node<K,V>[] prevNode = new Node[level];
        boolean exist = false;
        for (int i = level -1 ; i >= 0; i--) {

            int comp = -1;

            while (node.nexts[i] != null && (comp = compare(key, node.nexts[i].key)) > 0) {
                //
                node = node.nexts[i];
            }
            // 第i层的前驱
            prevNode[i] = node;

            if (comp == 0) {
                exist = true;
            }
        }

        if (!exist) return null;

        size--;

        Node<K, V> removeNode = prevNode[0].nexts[0];
        for (int i = 0; i < removeNode.nexts.length; i++) {
            prevNode[i].nexts[i] = removeNode.nexts[i];
        }

        // 修改level
        int newLevel = level;
        while (--newLevel >=0 && head.nexts[newLevel] == null) {
            level = newLevel;
        }

        return removeNode.value;
    }

    private static class Node<K,V> {
        K key;
        V value;
        Node<K,V> nexts[];
        public Node() {
        }
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            nexts = new Node[level];
        }
        @Override
        public String toString() {
            return key + ":" + value + "_" + nexts.length;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("一共" + level + "层").append("\n");
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = head;
            while (node.nexts[i] != null) {
                sb.append(node.nexts[i]);
                sb.append(" ");
                node = node.nexts[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
