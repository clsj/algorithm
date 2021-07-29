package com.sj.sequence;

import com.sj.utils.Asserts;

public class IndexOfTest {
    public static void main(String[] args) {
        Asserts.test(KMP.strStr("HellooWldoor", "oor") == 9);
        Asserts.test(KMP.strStr("0100101", "0101") == 3);
        Asserts.test(KMP.strStr("Hello World", "abc") == -1);
        Asserts.test(KMP.strStr("", "") == 0);
        Asserts.test(KMP.strStr("mississippi", "a") == -1);
    }
}
