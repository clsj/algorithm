package com.sj;

public class ArrayListInt {

    private int size;

    int[] elements;

    private static final int DEFAULT_CAPACITY = 2;

    public ArrayListInt(int initialCapacity) {
        initialCapacity = Math.max(initialCapacity, DEFAULT_CAPACITY);
        elements = new int[initialCapacity];
    }

    public ArrayListInt() {
        this(DEFAULT_CAPACITY);
    }

    // 元素的数量
    public int size(){
        return size;
    }

    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 是否包含某个元素
    public boolean contains(int element) {
        return indexOf(element) != -1;
    }

    // 添加元素到最后面
    public void add(int element) {
        add(size, element);
    }

    // 返回index位置对应的元素
    public int get(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size: " + size);
        }

        return elements[index];
    }

    // 设置index位置的元素
    public int set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size: " + size);
        }
        int old = elements[index];
        elements[index] = element;
        return old;
    }

    // 往index位置添加元素
    public void add(int index, int element) {
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

        int[] newElements = new int[newCapacity];

        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;

        System.out.println("arrayList扩容 old:" + oldCapacity + " new:" + newCapacity);
    }

    // 删除index位置对应的元素
    public int remove(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size: " + size);
        }

        int old = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size-- - index);
        return old;
    }

    // 查看元素的位置
    public int indexOf(int element) {

        for (int i = 0; i <= size; i++) {
            if (element == elements[i])
                return i;
        }

        return -1;
    }

    // 清除所有元素
    public void clear() {
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
