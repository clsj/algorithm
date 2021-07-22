package com.sj.union;


// 路径优化
public class UnionFind_QU_R_PC extends UnionFind_QU_R{

    public UnionFind_QU_R_PC() {
        super();
    }

    public UnionFind_QU_R_PC(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        checkIndex(v);
        // 当v和parents[v]相等才行
        if (v != parents[v]) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}