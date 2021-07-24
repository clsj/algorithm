package com.sj;

import java.util.*;

public abstract class AbstractGraph<V, E> {

    protected WeightManager<E> weightManager;

    AbstractGraph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    // 边的数量
    public abstract int edgesSize();

    // 顶点的数量
    public abstract int verticesSize();

    // 添加顶点
    public abstract void addVertex(V v);

    // 添加边
    public abstract void addEdge(V from, V to);

    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeVertex(V v);

    public abstract void removeEdge(V from, V to);

    // 广度优先搜索
    public abstract void bfs(V begin, Visitor<V> visitor);

    // 深度优先搜索
    public abstract void dfs(V begin, Visitor<V> visitor);

    // 拓扑排序
    public abstract List<V> topologicalSort();

    public abstract Set<EdgeInfo<V, E>> mst();

    // 获取最短路径 V到达的节点 E最短路径
    public abstract Map<V, E> shortestPath1(V begin);

    public abstract Map<V, PathInfo<V, E>> shortestPath2(V begin);

    public static abstract class Visitor<V> {
        public abstract void visit(V value);
    }

    public static class PathInfo<V, E> {
        List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();
        E weight;

        public PathInfo(E weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "PathInfo{" +
                    "edgeInfos=" + edgeInfos +
                    ", weight=" + weight +
                    '}';
        }
    }


    public static class EdgeInfo<V, E> {
        V from;
        V to;
        E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
