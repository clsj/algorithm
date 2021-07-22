package com.sj.union;

import java.util.Arrays;

public class UnionFind_QU_S extends UnionFind_QU{

    private int[] size;

    public UnionFind_QU_S() {
        super();
    }

    public UnionFind_QU_S(int capacity) {
        super(capacity);
        size = new int[Math.max(capacity, DEFAULT_CAPACITY)];
        Arrays.fill(size, 1);
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) {
            return;
        }

        if (size[p1] > size[p2]) {
            // 把小的嫁接到大的上边
            parents[p2] = p1;
            size[p1] = size[p2] + size[p1];
        }else {
            parents[p1] = p2;
            size[p2] = size[p2] + size[p1];
        }
    }
}