package com.sj.union;

import com.sj.utils.Asserts;
import com.sj.utils.Times;

public class UnionFindTest {

    public static final int count = 2000000;

    public static void test(UnionFind uf) {
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
//        test(new UnionFind_QU_S(12));
//        test(new UnionFind_QU(12));
//        test(new UnionFind_QF(12));
//        test(new UnionFind_QU_R(12));
//        test(new UnionFind_QU_R_PC(12));
//        test(new UnionFind_QU_R_PS(12));
//        test(new UnionFind_QU_R_PH(12));


//        testTime(new UnionFind_QF(count));
//        testTime(new UnionFind_QU(count));
        testTime(new UnionFind_QU_S(count));
        testTime(new UnionFind_QU_R(count));
        testTime(new UnionFind_QU_R_PC(count));
        testTime(new UnionFind_QU_R_PS(count));
        testTime(new UnionFind_QU_R_PH(count));

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
