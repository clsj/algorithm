package com.sj;

import java.util.Comparator;

public class RBTree<E> extends BBST<E>{

    private static final Boolean RED = false;
    private static final Boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        if (parent == null) {
            black(node);
            return;
        }

        // 如果是黑色什么都不需要动
        if (isBlack(parent)) {
            return;
        }

        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;

        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            afterAdd(red(grand));
            return;
        }

        // LL RR 对应的parent 和 node

        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                // LL
                black(parent);
                red(grand);
                rotateRight(grand);
            }else {
                // LR
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else {
            if (node.isLeftChild()) {
                // RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            }else {
                // RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {

        // 如果node是黑色
        // 查看用于替代它的节点是红色 染黑
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        // 如果是黑色 处理
        if (parent == null) {
            if (node.isLeaf()) {
                rootNode = null;
            }
            return;
        }

        boolean left = parent.left == null || node.isLeftChild();

        Node<E> sibling = left ? parent.right : parent.left;
        // 兄弟是否是黑色
        if (left) {
            // 被删除的在左边，兄弟节点在右边
            // 被删除的在右边，兄弟节点在左边
            if (isRed(sibling)) {
                // 兄弟节点为红色 变成黑色然后处理
                black(sibling);
                red(parent);
                rotateLeft(parent);
                sibling = parent.right;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有红色的 向下合并
                boolean parentBlack = isBlack(parent);

                black(parent);
                red(sibling);

                if (parentBlack) {
                    afterRemove(parent);
                }

            }else {
                // 可以借元素
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.right);
                rotateLeft(parent);
            }
        }else {
            // 被删除的在右边，兄弟节点在左边
            if (isRed(sibling)) {
                // 兄弟节点为红色 变成黑色然后处理
                black(sibling);
                red(parent);
                rotateRight(parent);
                sibling = parent.left;
            }

            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有红色的 向下合并
                boolean parentBlack = isBlack(parent);

                black(parent);
                red(sibling);

                if (parentBlack) {
                    afterRemove(parent);
                }

            }else {
                // 可以借元素
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent));
                black(parent);
                black(sibling.left);
                rotateRight(parent);
            }

        }

        // 看你能不能借元素
        // 不能接看父节点是否为红色
    }

//    protected void afterRemove(Node<E> node, Node<E> replaceNode) {
//
//        // 删除节点只会发生在子节点
//        // 如果node为红色 什么都不需要管
//        if (isRed(node)) {
//            return;
//        }
//
//        // 如果node是黑色
//        // 查看用于替代它的节点是红色 染黑
//        if (isRed(replaceNode)) {
//            black(replaceNode);
//            return;
//        }
//        Node<E> parent = node.parent;
//        // 如果是黑色 处理
//        if (parent == null) {
//            if (node.isLeaf()) {
//                rootNode = null;
//            }
//            return;
//        }
//
//        boolean left = parent.left == null || node.isLeftChild();
//
//        Node<E> sibling = left ? parent.right : parent.left;
//        // 兄弟是否是黑色
//        if (left) {
//            // 被删除的在左边，兄弟节点在右边
//            // 被删除的在右边，兄弟节点在左边
//            if (isRed(sibling)) {
//                // 兄弟节点为红色 变成黑色然后处理
//                black(sibling);
//                red(parent);
//                rotateLeft(parent);
//                sibling = parent.right;
//            }
//
//            if (isBlack(sibling.left) && isBlack(sibling.right)) {
//                // 兄弟节点没有红色的 向下合并
//                boolean parentBlack = isBlack(parent);
//
//                black(parent);
//                red(sibling);
//
//                if (parentBlack) {
//                    afterRemove(parent, null);
//                }
//
//            }else {
//                // 可以借元素
//                if (isBlack(sibling.right)) {
//                    rotateRight(sibling);
//                    sibling = parent.right;
//                }
//                color(sibling, colorOf(parent));
//                black(parent);
//                black(sibling.right);
//                rotateLeft(parent);
//            }
//        }else {
//            // 被删除的在右边，兄弟节点在左边
//            if (isRed(sibling)) {
//                // 兄弟节点为红色 变成黑色然后处理
//                black(sibling);
//                red(parent);
//                rotateRight(parent);
//                sibling = parent.left;
//            }
//
//            if (isBlack(sibling.left) && isBlack(sibling.right)) {
//                // 兄弟节点没有红色的 向下合并
//                boolean parentBlack = isBlack(parent);
//
//                black(parent);
//                red(sibling);
//
//                if (parentBlack) {
//                    afterRemove(parent, null);
//                }
//
//            }else {
//                // 可以借元素
//                if (isBlack(sibling.left)) {
//                    rotateLeft(sibling);
//                    sibling = parent.left;
//                }
//                color(sibling, colorOf(parent));
//                black(parent);
//                black(sibling.left);
//                rotateRight(parent);
//            }
//
//        }
//
//        // 看你能不能借元素
//        // 不能接看父节点是否为红色
//    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return null;
        }
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        if (node == null) {
            return null;
        }
        ((RBNode<E>)node).color = RED;
        return node;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private Node<E> black(Node<E> node) {
        if (node == null) {
            return null;
        }
        ((RBNode<E>)node).color = BLACK;
        return node;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    @Override
    protected Node<E> createNode(E element) {
        return new RBNode<>(element);
    }

    private static class RBNode<E> extends Node<E> {

        // 优先默认红色 保证除了第4条都满足
        boolean color = RED;

        public RBNode(E element) {
            super(element);
        }

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }

    @Override
    public Object string(Object node) {
        RBNode<E> rbNode = (RBNode<E>) node;
        return (rbNode.color == RED ? "R_" : "B_") + rbNode.element;
    }
}
