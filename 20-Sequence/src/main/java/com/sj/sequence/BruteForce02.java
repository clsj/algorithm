package com.sj.sequence;

public class BruteForce02 {
    public static int strStr(String haystack, String needle) {

        int hLen = haystack.length();
        int nLen = needle.length();

        if (nLen == 0) return 0;
        if (nLen > hLen) return -1;

        for (int h = 0; h <= hLen - nLen; h++) {
            boolean flag = true;
            for (int n = 0; n < nLen; n++) {
                if (haystack.charAt(h + n) != needle.charAt(n)) {
                    flag = false;
                    break;
                }
            }
            if (flag) return h;
        }

        return -1;
    }
}
