package com.sj;

public class LinkedListV2<E> extends AbstractList<E>{

    private Node<E> first;

    private Node<E> last;

    public LinkedListV2() {
        first = new Node<>(null, null, null);
        last = new Node<>(first, null, null);
        first.next = last;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
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

        rangCheckIndex(index);

        Node<E> node = new Node<>(null, element, null);
        Node<E> left = null;
        Node<E> right = null;

        if (index == 0) {
            // 找到first
            left = first;
            right = first.next;
        }else {
            right = getNode(index);
            left = right.prev;
        }

        left.next = node;
        node.prev = left;

        node.next = right;
        right.prev = node;

        size++;
    }

    private Node<E> getNode(int index) {

        rangCheckIndex(index);

        if (index < (size << 1)) {
            Node<E> node = first.next;
            // 从前边去
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else {

            Node<E> node = last.next;
            // 从前边去
            for (int i = size - 1 ; i >- index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public E get(int index) {

        rangCheckIndex(index);

        Node<E> node = getNode(index);

        return node.element;
    }

    @Override
    public E set(int index, E element) {
        rangCheckIndex(index);
        Node<E> node = getNode(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public E remove(int index) {
        rangCheckIndex(index);
        Node<E> node = getNode(index);
        E element = node.element;
        Node<E> right = node.next;
        Node<E> left = node.prev;
        left.next = right;
        right.prev = left;
        size--;
        return element;
    }

    @Override
    public int indexOf(E element) {

        Node<E> node = first.next;

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
        first = null;
        last = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(" elements:");
        sb.append("[");
        Node<E> next = first.next;
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
