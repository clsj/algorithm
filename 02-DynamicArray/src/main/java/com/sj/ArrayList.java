package com.sj;

public class ArrayList<E> implements List<E>{

    private int size;

    E[] elements;

    private static final int DEFAULT_CAPACITY = 2;

    public ArrayList(int initialCapacity) {
        initialCapacity = Math.max(initialCapacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[initialCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    // 元素的数量
    @Override
    public int size(){
        return size;
    }

    // 是否为空
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 是否包含某个元素
    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    // 添加元素到最后面
    @Override
    public void add(E element) {
        add(size, element);
    }

    // 返回index位置对应的元素
    @Override
    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size: " + size);
        }

        return elements[index];
    }

    // 设置index位置的元素
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size: " + size);
        }
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    // 往index位置添加元素
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size: " + size);
        }

        ensureCapacity(size + 1);

        System.arraycopy(elements, index, elements, index + 1, size++ - index);
        elements[index] = element;
    }

    private void ensureCapacity(int capacity) {

        int oldCapacity = elements.length;

        if (oldCapacity >= capacity) {
            return;
        }

        int newCapacity = oldCapacity + (oldCapacity >> 1);

        E[] newElements = (E[])new Object[newCapacity];

        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;

        System.out.println("arrayList扩容 old:" + oldCapacity + " new:" + newCapacity);
    }

    // 删除index位置对应的元素
    @Override
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size: " + size);
        }

        E old = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);

        elements[--size] = null;
        return old;
    }

    // 查看元素的位置
    @Override
    public int indexOf(E element) {

        if (element == null) {

            for (int i = 0; i < size; i++) {
                if (elements[i] == null){
                    return i;
                }
            }

        }else {
            for (int i = 0; i <= size; i++) {
                if (element.equals(elements[i]))
                    return i;
            }

        }


        return -1;
    }

    // 清除所有元素
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(" elements:");
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            sb.append(",");
        }

        if (size > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }
}
