package com.sj.union;

public class UnionFind_QU extends UnionFind{

    public UnionFind_QU() {
        super();
    }

    public UnionFind_QU(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {

        checkIndex(v);

        // 当v和parents[v]相等才行
        while (v != parents[v]) {
            v = parents[v];
        }

        return v;
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) {
            return;
        }

        parents[p1] = p2;
    }
}
