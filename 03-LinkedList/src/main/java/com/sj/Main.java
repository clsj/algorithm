package com.sj;

public class Main {

    public static void main(String[] args) {
        LinkedListV1<String> list = new LinkedListV1<>();

        list.add("1111");
        list.add("2222");
        list.add("333");
        list.add("4444");
        list.add("555");
        list.add("6666");
        list.add("7777");


        System.out.println(list);

        System.out.println(list.indexOf("1111"));

//        System.out.println(list.set(6, "8888"));
//        System.out.println(list);

    }

}
