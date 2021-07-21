package com.sj.sort;


import com.sj.utils.Integers;

public class BinarySearch {

    public static void main(String[] args) {
//        Integer[] data = Integers.ascOrder(1, 20);
        Integer[] data = new Integer[] {2, 2, 4, 4, 6, 8, 8, 10};
        Integers.println(data);
        System.out.println(BinarySearch.search(data, 12, data.length - 1));;
    }

    // 二分查找
    public static int indexOf(Integer[] elements, Integer value) {

        if (elements == null || elements.length == 0) return -1;

        int begin = 0;
        int end = elements.length;

        while (end > begin) {
            int mid = (end + begin) >> 1;
            if (value > elements[mid]) {
                begin = mid + 1;
            }else if (value < elements[mid]) {
                end = mid;
            }else {
                return mid;
            }
        }
        return -1;

    }

    // 查找第一个大于value的位置
    public static int search(Integer[] elements, Integer value, int end) {

        if (elements == null || elements.length == 0) return -1;

        int begin = 0;

        while (end > begin) {
            int mid = (end + begin) >> 1;
            if (value >= elements[mid]) {
                begin = mid + 1;
            }else if (value < elements[mid]) {
                end = mid;
            }
        }
        return begin;

    }
}
