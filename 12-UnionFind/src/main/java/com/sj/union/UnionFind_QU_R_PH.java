package com.sj.union;


// 路径优化
// 路径减半
public class UnionFind_QU_R_PH extends UnionFind_QU_R{

    public UnionFind_QU_R_PH() {
        super();
    }

    public UnionFind_QU_R_PH(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        checkIndex(v);
        // 当v和parents[v]相等才行
        while (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}