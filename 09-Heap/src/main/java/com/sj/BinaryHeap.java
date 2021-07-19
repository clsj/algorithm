package com.sj;

import com.sj.printer.BinaryTreeInfo;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    // 左节点 2n + 1  右节点 2n + 2
    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);

        if (elements == null) {
            this.elements = (E[])new Object[DEFAULT_CAPACITY];
        }else {
            int capacity = Math.max(DEFAULT_CAPACITY, elements.length);
            this.elements = (E[])new Object[capacity];
            size = elements.length;
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }


    private void heapify() {
        // 至上而下的上滤
//        for (int i = 1; i < size; i++) {
//            siftUp(i);
//        }

        // 至下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0 ; i--) {
            siftDown(i);
        }


    }


//    private void heapify() {
//    }

    public BinaryHeap(E[] elements) {
        this(elements, null);
    }

    // 左节点 2n + 1  右节点 2n + 2
    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap() {
        this(null, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();

        E element = elements[0];
        elements[0] = elements[size-1];
        elements[size-1] = null;
        size--;
        siftDown(0);
        return element;
    }

    @Override
    public E replace(E e) {
        E element = elements[0];
        elements[0] = e;
        siftDown(0);
        return element;
    }

    private void siftUp(int index) {
        E element = elements[index];

        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(parent, element) >= 0) {
                break;
            }
            elements[index] = parent;
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void siftDown(int index) {
        E element = elements[index];

        int left = (index << 1) + 1;
        int right = (index << 1) + 2;

        int maxElementIndex = getMaxElementIndex(left, right);
        if (maxElementIndex == -1) {
            return;
        }

        if (compare(element, elements[maxElementIndex]) >= 0) {
            return;
        }

        // 交换
        elements[index] = elements[maxElementIndex];
        elements[maxElementIndex] = element;
        siftDown(maxElementIndex);

    }

    private int getMaxElementIndex(int left, int right) {
        if (left >= size) {
            return -1;
        }

        if (right >= size) {
            return left;
        }

        return compare(elements[left], elements[right]) > 0 ? left : right;
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

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 1;

        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {

        Integer index = (Integer) node;
        E element = elements[index];
        if (element == null) {
            return "null";
        }
        return element;
    }
}
