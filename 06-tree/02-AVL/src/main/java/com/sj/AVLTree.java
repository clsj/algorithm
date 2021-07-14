package com.sj;

import java.util.Comparator;

/**
 * @author li
 * @since 2021/7/14
 */
public class AVLTree<E> extends BST<E>{

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        super.afterAdd(node);
    }
}
