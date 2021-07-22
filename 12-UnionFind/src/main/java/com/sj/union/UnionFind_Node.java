package com.sj.union;

import java.util.Comparator;

public abstract class UnionFind_Node<E> {
    private Comparator<E> comparator;
    protected Node<E>[] nodes;

    protected UnionFind_Node(E[] elements) {
        this(elements, null);
    }

    protected UnionFind_Node(E[] elements, Comparator<E> comparator) {
        this.comparator = comparator;
        nodes = new Node[elements.length];
        for (int i = 0; i < elements.length; i++) {
            Node<E> node = new Node<>();
            node.parent = node;
            node.value = elements[i];
            node.rank = 1;
            nodes[i] = node;
        }
    }

    // 查找索引的根节点
    public abstract E find(int v);

    public abstract void union(int v1, int v2);


    public int compare(E e1, E e2) {

        if (comparator != null) {
            return comparator.compare(e1, e2);
        }

        return ((Comparable<E>)e1).compareTo(e2);
    }

    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void checkIndex(int v) {
        if (v <0 || v >= nodes.length) {
            throw new ArrayIndexOutOfBoundsException("v越界");
        }
    }
}
