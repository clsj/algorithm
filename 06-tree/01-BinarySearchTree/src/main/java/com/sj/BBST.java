package com.sj;

import java.util.Comparator;

public class BBST<E> extends BST<E> {

    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;

        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;

        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    // 更改parent的值 以及高度值
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // parent的父为grand的父节点
        parent.parent = grand.parent;
        // grand的父节点的左右
        if (grand.isLeftChild()) {
            parent.parent.left = parent;
        }else if (grand.isRightChild()){
            parent.parent.right = parent;
        }else {
            rootNode = parent;
        }

        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;
    }

    // 统一旋转操作 r是旋转的根节点 d是旋转过后的根节点
    protected void rotate(
            Node<E> r,
            Node<E> a,Node<E> b,Node<E> c,
            Node<E> d,
            Node<E> e,Node<E> f,Node<E> g) {

        // 处理根节点
        d.parent = r.parent;

        if (r.isRightChild()) {
            d.parent.right = d;
        }else if (r.isLeftChild()) {
            d.parent.left = d;
        }else {
            rootNode = d;
        }

        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }


        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
