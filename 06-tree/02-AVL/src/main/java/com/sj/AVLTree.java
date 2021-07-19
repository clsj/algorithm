package com.sj;

import java.util.Comparator;

/**
 * @author li
 * @since 2021/7/14
 */
public class AVLTree<E> extends BBST<E>{

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            // 判断是否平衡
            if (isBalance((AVLNode<E>)node)) {
                // 平衡更新高度
                ((AVLNode<E>)node).updateHeight();
            }else {
                // 不平衡让其平衡
                rebalance((AVLNode<E>)node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            // 判断是否平衡
            if (isBalance((AVLNode<E>)node)) {
                // 平衡更新高度
                ((AVLNode<E>)node).updateHeight();
            }else {
                // 不平衡让其平衡
                rebalance((AVLNode<E>)node);
            }
        }
    }

    private void rebalance(AVLNode<E> grand) {
        AVLNode<E> parent = grand.tallerChild();
        AVLNode<E> node = parent.tallerChild();

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                // LL
                // grand右旋
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            }else {
                // LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }

        }else {
            if (node.isLeftChild()) {
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            }else {
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }


    private void rebalance2(AVLNode<E> grand) {
        AVLNode<E> parent = grand.tallerChild();
        AVLNode<E> node = parent.tallerChild();

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                // LL
                // grand右旋
                rotateRight(grand);
            }else {
                // LR
                // parent左旋  grand右旋
                rotateLeft(parent);
                rotateRight(grand);
            }

        }else {
            if (node.isLeftChild()) {
                // RL
                // parent右旋  grand左旋
                rotateRight(parent);
                rotateLeft(grand);
            }else {
                // RR
                // grand左旋
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>)node).updateHeight();
    }

    @Override
    protected Node<E> createNode(E element) {
        return new AVLNode<>(element);
    }

    public boolean isBalance(AVLNode<E> node) {
        return Math.abs(node.balanceFactor()) <= 1;
    }

    private static class AVLNode<E> extends Node<E> {

        int height = 1;

        public AVLNode(E element) {
            super(element);
        }

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        // 高度为左右最大的高 + 1
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public AVLNode<E> tallerChild() {

            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;

            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;

            if (leftHeight > rightHeight) return (AVLNode<E>)left;

            if (leftHeight < rightHeight) return (AVLNode<E>)right;

            return isLeftChild() ? (AVLNode<E>)left : (AVLNode<E>)right;
        }

    }

    @Override
    public Object string(Object node) {

        AVLNode<E> node1 = (AVLNode<E>) node;
        String str = "";
        Node<E> parent = node1.parent;

        if (parent != null) {
            str = " p:" + parent.element.toString();
        }

//        return "[" + node1.element + "->h:" + node1.height + "->b:" + node1.balanceFactor() + "]";
        return node1.element;
    }
}
