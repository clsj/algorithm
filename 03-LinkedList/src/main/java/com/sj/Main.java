package com.sj;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        List<String> list = new LinkedListV2<>();

        list.add("1111");
        list.add("2222");
        list.add("333");
        list.add("4444");
        list.add("555");
        list.add("6666");
        list.add("7777");
        list.remove(0);

        System.out.println(list);

        System.out.println(list.indexOf("2222"));

        System.out.println(list.set(6, "8888"));
        System.out.println(list.set(6, "8888"));
        System.out.println(list);
    }

}
