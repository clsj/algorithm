package com.sj;

import java.util.List;
import java.util.Set;

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
    void bfs(V begin, Visitor<V> visitor);

    // 深度优先搜索
    void dfs(V begin, Visitor<V> visitor);

    public static abstract class Visitor<V> {
        public abstract void visit(V value);
    }

    // 拓扑排序
    List<V> topologicalSort();

    Set<EdgeInfo<V, E>> mst();

    class EdgeInfo<V, E> {
        V from;
        V to;
        E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
