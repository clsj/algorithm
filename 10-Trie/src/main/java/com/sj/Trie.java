package com.sj;

import java.util.HashMap;
import java.util.Map;

public class Trie<V> implements ITrie<V>{

    private int size;

    private final Node<V> rootNode;

    public Trie() {
        rootNode = new Node<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        rootNode.children.clear();
    }

    @Override
    public V get(String key) {
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean contains(String key) {
        return node(key) != null;
    }

    @Override
    public V add(String key, V value) {

        checkKeyNotEmpty(key);

        Node<V> node = rootNode;

        for (int i = 0; i < key.length(); i++) {
            String s = String.valueOf(key.charAt(i));

            Node<V> pNode = node.children.get(s);

            if (pNode == null) {
                Node<V> pNode2 = new Node<>(node, s);
                node.children.put(s, pNode2);
                node = pNode2;
            }else {
                node = pNode;
            }
        }

        node.value = value;
        node.word = true;

        return value;
    }

    @Override
    public V remove(String key) {

        checkKeyNotEmpty(key);

        Node<V> node = node(key);

        if (node == null) {
            return null;
        }

        V value = node.value;

        node.word = false;
        node.value = null;
        // 说明node是单词
        // 删除条件 node不是root node的孩子为empty node的parentNode有值 node不是单词

        Node<V> parentNode = null;

        while (node != rootNode && node.children.size() == 0 && (parentNode = node.parent) != null && !node.word) {
            // 删除
            parentNode.children.remove(node.character);
            node = parentNode;
        }

        return value;
    }

    @Override
    public boolean startsWith(String prefix) {

        checkKeyNotEmpty(prefix);

        Node<V> node = rootNode;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            node = node.children.get(c + "");

            if (node == null) {
                return false;
            }
        }

        return true;
    }

    // 根据key获取node
    private Node<V> node(String key) {
        checkKeyNotEmpty(key);

        Node<V> node = rootNode;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);

            node = node.children.get(c + "");

            if (node == null) {
                return null;
            }
        }

        return node.word ? node : null;
    }

    private void checkKeyNotEmpty(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key not empty");
        }
    }

    private static class Node<V> {

        Node<V> parent;

        Map<String, Node<V>> children = new HashMap<>();

        String character;

        // 是否为单词
        boolean word;

        V value;

        public Node () {
            this(null, null);
        }

        public Node (Node<V> parent) {
            this(parent, null);
        }

        public Node (Node<V> parent, String character) {
            this.parent = parent;
            this.character = character;
        }
    }


}
