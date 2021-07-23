package com.sj;

public interface Graph<V, E> {

    // 边的数量
    int edgesSize();

    // 顶点的数量
    int verticesSize();

    // 添加顶点
    void addVertex(V v);

    // 添加边
    void addEdge(V from, V to);

    void addEdge(V from, V to, E weight);


    void removeVertex(V v);

    void removeEdge(V from, V to);

    // 广度优先搜索
    void bfs(V begin);

    // 深度优先搜索
    void dfs(V begin);
}
