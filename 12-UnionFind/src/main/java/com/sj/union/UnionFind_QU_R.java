package com.sj.union;

import java.util.Arrays;

public class UnionFind_QU_R extends UnionFind_QU{

    private int[] rank;

    public UnionFind_QU_R() {
        super();
    }

    public UnionFind_QU_R(int capacity) {
        super(capacity);
        rank = new int[Math.max(capacity, DEFAULT_CAPACITY)];
        Arrays.fill(rank, 1);
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) {
            return;
        }

        if (rank[p1] > rank[p2]) {
            // 把小的嫁接到大的上边
            parents[p2] = p1;
        }else if (rank[p1] < rank[p2]){
            parents[p1] = p2;
        }else {
            parents[p1] = p2;
            rank[p2] += 1;
        }
    }
}