package com.sj;

import java.util.LinkedList;

public class Stack<E> {

    private final LinkedList<E> list = new LinkedList<>();

    public int size (){
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.addLast(element);
    }

    public E pop() {
        return list.removeLast();
    }

    public E top() {
        return list.getLast();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

}
