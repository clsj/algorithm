package com.sj;

interface List<E> {

    // 元素的数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 是否包含某个元素
    boolean contains(E element);

    // 添加元素到最后面
    void add(E element);

    // 返回index位置对应的元素
    E get(int index) ;

    // 设置index位置的元素
    E set(int index, E element) ;

    void add(int index, E element);

    // 删除index位置对应的元素
    E remove(int index) ;

    // 查看元素的位置
    int indexOf(E element);

    // 清除所有元素
    void clear();
}
