package com.sj.sequence;

public class KMP {

//    private static int[] next(String needle) {
//        int len = needle.length();
//        int[] next = new int[len];
//
//        int i = 0;
//        int n = next[0] = -1;
//
//        while (i < len - 1) {
//            if (n < 0 || needle.charAt(i) == needle.charAt(n)) {
//                next[++i] = ++n;
//            }else {
//                n = next[n];
//            }
//        }
//        return next;
//    }

    private static int[] next2(String needle) {
        int len = needle.length();
        int[] next = new int[len];

        int i = 0;
        int n = next[0] = -1;

        while (i < len - 1) {
            if (n < 0 || needle.charAt(i) == needle.charAt(n)) {
                i++;
                n++;
                if (needle.charAt(i) == needle.charAt(n)) {
                    next[i] = next[n];
                }else {
                    next[i] = n;
                }
            }else {
                n = next[n];
            }
        }
        return next;
    }


    public static int strStr(String haystack, String needle) {

        int hLen = haystack.length();
        int nLen = needle.length();

        if (nLen == 0) return 0;
        if (nLen > hLen) return -1;

        int nIndex = 0;
        int hIndex = 0;

        int[] next = next2(needle);

        while (hIndex < hLen) {
            if (haystack.charAt(hIndex) == needle.charAt(nIndex)) {
                nIndex++;
                hIndex++;
            }else {
                if (next[nIndex] == -1) {
                    nIndex = 0;
                    hIndex++;
                }else {
                    nIndex = next[nIndex];
                }
            }

            if (nIndex == nLen) {
                return hIndex - nIndex;
            }
        }

        return -1;
    }
}
