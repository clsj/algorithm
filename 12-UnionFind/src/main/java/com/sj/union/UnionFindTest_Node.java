package com.sj.union;

import com.sj.utils.Asserts;
import com.sj.utils.Integers;
import com.sj.utils.Times;

public class UnionFindTest_Node {

    public static final int count = 2000000;

    public static void test(UnionFind_Node uf) {
        uf.union(0,1);
        uf.union(0,4);
        uf.union(0,3);
        uf.union(3,2);
        uf.union(2,5);

        uf.union(6,7);

        uf.union(8,9);
        uf.union(8,10);
        uf.union(9,11);

        Asserts.test(uf.isSame(1, 5));
        Asserts.test(!uf.isSame(1, 7));
        Asserts.test(!uf.isSame(1, 8));
        Asserts.test(!uf.isSame(6, 8));
        uf.union(1, 6);

        Asserts.test(uf.isSame(1, 7));
        Asserts.test(!uf.isSame(1, 8));
        uf.union(1, 8);
        Asserts.test(uf.isSame(6, 8));
    }

    public static void main(String[] args) {
        Integer[] data = Integers.ascOrder(0, 11);
        test(new UnionFind_QF_Node<Integer>(data));

    }

    public static void testTime(UnionFind uf) {

        Times.test(uf.getClass().getName(), new Times.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < count; i++) {
                    uf.union((int)(Math.random() * count), (int)(Math.random() * count));
                }

                for (int i = 0; i < count; i++) {
                    uf.isSame((int)(Math.random() * count), (int)(Math.random() * count));
                }
            }
        });


    }

}
