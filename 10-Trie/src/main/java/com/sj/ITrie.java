package com.sj;

public interface ITrie<V> {

    int size();

    boolean isEmpty();

    void clear();

    V get(String key);

    boolean contains(String key);

    V add(String key, V value);

    V remove(String key);

    boolean startsWith(String prefix);
}
