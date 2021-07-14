package com.sj;

import com.sj.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author li
 * @since 2021/7/14
 */
public abstract class BinaryTree<E> implements TreeInterface<E> , BinaryTreeInfo {

    protected int size;

    protected Node<E> rootNode;

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        int height;
        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
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
        rootNode = null;
        size = 0;
    }

    protected void checkElementNotNull(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    public static abstract class Visitor<E> {
        boolean stop;
        /**
         * @return 如果返回true，就代表停止遍历
         */
        public abstract boolean visit(E element);
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(Visitor<E> visitor) {
        preorderTraversal(rootNode, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null || visitor.stop) return;

        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(Visitor<E> visitor) {
        inorderTraversal(rootNode, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {

        if (node == null || visitor == null || visitor.stop) return;

        inorderTraversal(node.left, visitor);
        if (!visitor.stop) {
            visitor.stop = visitor.visit(node.element);
        }

        inorderTraversal(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal(Visitor<E> visitor) {
        postorderTraversal(rootNode, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {

        if (node == null || visitor == null || visitor.stop) return;

        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        if (!visitor.stop) {
            visitor.stop = visitor.visit(node.element);
        }
    }

    /**
     * 层序遍历
     * 1. 根节点入队列。
     * 2. 根节点出队列。
     * 3. 左子树，右子树依次入队列，循环至空。
     */
    public void levelOrderTraversal(Visitor<E> visitor) {
        if (rootNode == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);

        while (!queue.isEmpty() && !visitor.stop) {
            Node<E> node = queue.poll();

            visitor.stop = visitor.visit(node.element);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }







    public int height() {
        return height(rootNode);
    }

    public int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int i = Math.max(height(node.left), height(node.right));
        return i + 1;
    }

    public int height2() {

        if (rootNode == null) return 0;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);

        int height = 0;
        // 表示每一层的size
        int leaveSize = 1;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            leaveSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            // 表示这一层访问完毕
            if (leaveSize == 0) {
                // 当一层访问完毕后，队列的size即为下一层的个数
                leaveSize = queue.size();
                height++;
            }
        }

        return height;
    }

    // 判断是否为完全二叉树
    // 层序遍历连续的二叉树
    public boolean isComplete() {

        if (rootNode == null) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);

        boolean flag = false;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            // 如果左右都不为空 依次加入队列
            if (node.left != null && node.right != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }

            if (node.left == null && node.right != null) {
                return false;
            }

            // 判断之后是否全为叶子节点
            if ((node.left != null && node.right == null) || node.isLeaf()) {
                flag = true;
            }

            if (flag) {
                Node<E> peek = queue.peek();
                if (peek != null && !peek.isLeaf()) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isComplete02() {

        if (rootNode == null) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);

        boolean flag = false;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (flag && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            }else {
                flag = true;
            }
        }
        return true;
    }

    // 前驱节点
    // left有值 left 右右右
    // left没值, 在左边的父节点
    public Node<E> predecessor(Node<E> node) {

        if (node == null) {
            return null;
        }

        Node<E> pred = node.left;

        if (pred != null) {
            while (pred.right != null) {
                pred = pred.right;
            }
            return pred;
        }

        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }

        return node.parent;
    }

    // 后继节点
    public Node<E> successor(Node<E> node) {

        if (node == null) {
            return null;
        }

        Node<E> suc = node.right;

        if (suc != null) {
            while (suc.left != null) {
                suc = suc.left;
            }
            return suc;
        }

        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }

        return node.parent;
    }


    @Override
    public Object root() {
        return rootNode;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> node1 = (Node<E>) node;
        return node1.element;
    }

}
