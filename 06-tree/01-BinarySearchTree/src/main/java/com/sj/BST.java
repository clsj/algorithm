package com.sj;

import com.sj.printer.BinaryTrees;

import java.util.Comparator;

public class BST<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BST() {
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(E element) {

        checkElementNotNull(element);

        Node<E> node = new Node<>(element);

        if (rootNode == null) {
            rootNode = node;
            size++;
            afterAdd(node);
            return;
        }
        Node<E> cur = rootNode;
        // 获取添加的元素
        while (true) {
            if (compare(cur.element, element) > 0) {
                // 左边
                if (cur.left == null) {
                    cur.left = node;
                    node.parent = cur;
                    size++;
                    afterAdd(node);
                    break;
                }else {
                    cur = cur.left;
                }

            }else if (compare(cur.element, element) < 0){
                // 右边
                if (cur.right == null) {
                    cur.right = node;
                    node.parent = cur;
                    size++;
                    afterAdd(node);
                    break;
                }else {
                    cur = cur.right;
                }

            }else {
                cur.element = element;
                return;
            }
        }

    }

    protected void afterAdd(Node<E> node) {}

    private int compare(E e1, E e2) {

        if (comparator != null) {
            return comparator.compare(e1, e2);
        }

        return ((Comparable<E>)e1).compareTo(e2);
    }

    @Override
    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }

        // 度为2
        if (node.hasTwoChildren()) {
            // 获取前驱节点
            Node<E> preNode = predecessor(node);
            // 将前驱节点的值赋予该节点
            node.element = preNode.element;
            // 前驱节点赋予node，删除前驱节点
            node = preNode;
        }

        // 删除node 度为1 或者0
        Node<E> replaceNode = node.left != null ? node.left : node.right;

        if (node.parent == null) {
            replaceNode.parent = null;
            rootNode = replaceNode;
        }else {
            // 度为0和1通用
            if (node.parent.left == node) {
                // node是左节点
                node.parent.left = replaceNode;
            }else {
                // node是右节点
                node.parent.right = replaceNode;
            }
        }

        size--;
    }

    private Node<E> node(E element) {

        Node<E> node = rootNode;

        while (node != null) {

            int compare = compare(element, node.element);

            if (compare == 0) {
                return node;
            }

            if (compare > 0) {
                node = node.right;
            }

            if (compare < 0) {
                node = node.left;
            }
        }

        return null;
    }


    @Override
    public boolean contains(E element) {
        return node(element) != null;
    }

    @Override
    public Object string(Object node) {
        Node<E> node1 = (Node<E>) node;
        String str = "";
        Node<E> parent = node1.parent;

        if (parent != null) {
            str = " p:" + parent.element.toString();
        }

        return node1.element + str;
    }

}
