package com.sj;

public interface TreeInterface<E> {

    int size();

    boolean isEmpty();

    void clear();

    void add(E element);

    void remove(E element);

    boolean contains(E element);
}
