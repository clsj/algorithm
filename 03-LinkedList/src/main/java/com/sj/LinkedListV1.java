package com.sj;

public class LinkedListV1<E> implements List<E>{

    private int size;

    private Node<E> header;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {

        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size:" + size);
        }
        Node<E> node = new Node<>(element, null);
        // 找到最后一个
        Node<E> eNode = getNode(index - 1);
        node.next = eNode.next;
        eNode.next = node;
        size++;
    }

    private Node<E> getNode(int index) {
        if (index == -1) {

            if (header == null) {
                header = new Node<>(null, null);
            }

            return header;
        }
        Node<E> node = header.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E get(int index) {

        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size:" + size);
        }

        Node<E> node = getNode(index);

        return node.element;
    }

    @Override
    public E set(int index, E element) {

        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size:" + size);
        }

        Node<E> node = getNode(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + "  size:" + size);
        }
        Node<E> node = getNode(index - 1);
        E element = node.next.element;
        node.next = node.next.next;
        size--;
        return element;
    }

    @Override
    public int indexOf(E element) {

        Node<E> node = header.next;

        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (node.element == null) {
                    return i;
                }
            }else {
                if (element.equals(node.element)) {
                    return i;
                }
            }

            node = node.next;

        }
        return -1;
    }

    @Override
    public void clear() {
        size = 0;
        header = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(" elements:");
        sb.append("[");
        Node<E> next = header.next;
        for (int i = 0; i < size; i++) {
            sb.append(next.element);
            next = next.next;
            sb.append(" -> ");
        }

        if (size > 0) {
            sb.delete(sb.length() - 4, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}
