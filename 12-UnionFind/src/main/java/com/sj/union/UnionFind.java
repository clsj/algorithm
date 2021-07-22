package com.sj.union;

public abstract class UnionFind {

    protected int[] parents;

    protected static final int DEFAULT_CAPACITY = 10;

    protected UnionFind() {
        this(DEFAULT_CAPACITY);
    }

    protected UnionFind(int capacity) {
        parents = new int[Math.max(DEFAULT_CAPACITY, capacity)];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    // 查找索引的根节点
    public abstract int find(int v);

    public abstract void union(int v1, int v2);

    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void checkIndex(int v) {
        if (v <0 || v >= parents.length) {
            throw new ArrayIndexOutOfBoundsException("v越界");
        }
    }
}
