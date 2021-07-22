package com.sj.union;

public class UnionFind_QF extends UnionFind{

    public UnionFind_QF() {
        super();
    }

    public UnionFind_QF(int capacity) {
        super(capacity);
    }

    // 查找索引的根节点
    @Override
    public int find(int v) {
        checkIndex(v);
        return parents[v];
    }

    @Override
    public void union(int v1, int v2) {

        checkIndex(v1);
        checkIndex(v2);
        int p1 = parents[v1];
        int p2 = parents[v2];
        if (p1 == p2) {
            return;
        }

        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }

}
