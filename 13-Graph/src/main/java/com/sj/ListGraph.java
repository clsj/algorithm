package com.sj;

import java.util.*;

public class ListGraph<V, E> implements Graph<V, E>{

    // 存储顶点
    private final Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private final Set<Edge<V, E>> edges = new HashSet<>();

    public void print() {
        vertices.forEach((V value, Vertex<V, E> vertex) -> {
            System.out.println(vertex);
        });
        edges.forEach(System.out::println);
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {

        Vertex<V, E> vertex = vertices.get(v);

        if (vertex == null) {
            Vertex<V, E> veVertex = new Vertex<>(v);
            vertices.put(v, veVertex);
        }
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertices.get(from);

        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, E> toVertex = vertices.get(to);

        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        // 保证顶点
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;

        fromVertex.outEdges.remove(edge);
        toVertex.inEdges.remove(edge);
        edges.remove(edge);


        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    // 删除边
    @Override
    public void removeEdge(V from, V to) {

        Vertex<V, E> fromVertex = vertices.get(from);

        if (fromVertex == null) {
            return;
        }

        Vertex<V, E> toVertex = vertices.get(to);

        if (toVertex == null) {
            return;
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);

        fromVertex.outEdges.remove(edge);
        toVertex.inEdges.remove(edge);
        edges.remove(edge);
    }

    @Override
    public void removeVertex(V v) {
        // 删除顶点
        Vertex<V, E> vertex = vertices.remove(v);

        if (vertex == null) {
            return;
        }

        Set<Edge<V, E>> inEdges = vertex.inEdges;

        for (Edge<V, E> inEdge : inEdges) {
            // 进来的from
            Vertex<V, E> from = inEdge.from;
            Edge<V, E> edge = new Edge<>(from, inEdge.to);
            edges.remove(edge);
            // 进来的出去点 删除
            Set<Edge<V, E>> outEdges = from.outEdges;
            outEdges.remove(edge);
        }

        inEdges.clear();

        Set<Edge<V, E>> outEdges = vertex.outEdges;

        for (Edge<V, E> outEdge : outEdges) {
            Vertex<V, E> to = outEdge.to;
            Edge<V, E> edge = new Edge<>(outEdge.from, outEdge.to);
            edges.remove(edge);
            Set<Edge<V, E>> in = to.inEdges;
            in.remove(edge);
        }

        outEdges.clear();
    }

    void bfs(V begin, Visitor<V, E> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);

        if (beginVertex == null) {
            return;
        }
        Set<Vertex<V, E>> lisVertex = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        lisVertex.add(beginVertex);


        while (!queue.isEmpty()) {
            Vertex<V, E> pollVertex = queue.poll();
            visitor.visit(pollVertex);

            Set<Edge<V, E>> outEdges = pollVertex.outEdges;
            outEdges.forEach(outEdge -> {
                Vertex<V, E> to = outEdge.to;
                if (!lisVertex.contains(to)) {
                    queue.offer(to);
                    lisVertex.add(to);
                }
            });
        }
    }

    public static abstract class Visitor<V, E> {
        public abstract void visit(Vertex<V, E> vertex);
    }


    @Override
    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);

        if (beginVertex == null) {
            return;
        }
        Set<Vertex<V, E>> lisVertex = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        lisVertex.add(beginVertex);


        while (!queue.isEmpty()) {
            Vertex<V, E> pollVertex = queue.poll();
            System.out.println(pollVertex);

            Set<Edge<V, E>> outEdges = pollVertex.outEdges;
            outEdges.forEach(outEdge -> {
                Vertex<V, E> to = outEdge.to;
                if (!lisVertex.contains(to)) {
                    queue.offer(to);
                    lisVertex.add(to);
                }
            });
        }
    }

    @Override
    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        Set<Vertex<V, E>> lisVertex = new HashSet<>();
        if (beginVertex == null) {
            return;
        }
        dfs(beginVertex, lisVertex);
    }

    public void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> lisVertex) {
        System.out.println(vertex);
        lisVertex.add(vertex);
        Set<Edge<V, E>> outEdges = vertex.outEdges;
        outEdges.forEach(outEdge -> {
            if (!lisVertex.contains(outEdge.to)) {
                dfs(outEdge.to, lisVertex);
            }
        });
    }

    public static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    ", inEdges=" + inEdges +
                    ", outEdges=" + outEdges +
                    '}';
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from.value +
                    ", to=" + to.value +
                    ", weight=" + weight +
                    '}';
        }
    }

}
