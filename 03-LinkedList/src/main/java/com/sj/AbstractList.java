package com.sj;

public abstract class AbstractList<E> implements List<E>{

    public int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected void rangCheckIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size:" + size);
        }
    }
}
