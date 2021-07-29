package com.sj.sequence;

public class BruteForce {

    public static int strStr(String haystack, String needle) {

        int hLen = haystack.length();
        int nLen = needle.length();

        if (nLen == 0) return 0;

        if (nLen > hLen) return -1;

        int nIndex = 0;
        int hIndex = 0;

        while (true) {
            if (haystack.charAt(hIndex) == needle.charAt(nIndex)) {
                nIndex++;
                hIndex++;
            }else {
                hIndex = hIndex - nIndex + 1;
                nIndex = 0;
                if (hIndex > hLen - nLen) {
                    break;
                }
            }

            if (nIndex == nLen) {
                return hIndex - nIndex;
            }
        }

        return -1;
    }
}
