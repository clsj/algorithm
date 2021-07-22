package com.sj.union;

import java.util.Comparator;

public class UnionFind_QF_Node<E> extends UnionFind_Node<E>{

    protected UnionFind_QF_Node(E[] elements) {
        this(elements, null);
    }

    protected UnionFind_QF_Node(E[] elements, Comparator<E> comparator) {
        super(elements, comparator);
    }

    @Override
    public E find(int v) {
        checkIndex(v);
        return nodes[v].parent.value;
    }

    @Override
    public void union(int v1, int v2) {
        checkIndex(v1);
        checkIndex(v2);
        E n1 = nodes[v1].value;
        E n2 = nodes[v2].value;
        if (compare(n1, n2) == 0) {
            return;
        }

        for (Node<E> node : nodes) {
            if (compare(node.value, n1) == 0) {
                node.value = n2;
            }
        }
    }


}
