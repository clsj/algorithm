package com.sj.union;


// 路径优化
// 路径分裂
public class UnionFind_QU_R_PS extends UnionFind_QU_R{

    public UnionFind_QU_R_PS() {
        super();
    }

    public UnionFind_QU_R_PS(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        checkIndex(v);
        // 当v和parents[v]相等才行
        while (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }
}