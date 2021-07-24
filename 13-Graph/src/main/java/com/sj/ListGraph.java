package com.sj;

import java.util.*;

public class ListGraph<V, E> extends AbstractGraph<V, E> {

    // 存储顶点
    private final Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private final Set<Edge<V, E>> edges = new HashSet<>();

    ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

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


    @Override
    public void bfs(V begin, Visitor<V> visitor) {
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

            if (visitor != null) {
                visitor.visit(pollVertex.value);
            }else {
                System.out.println(pollVertex.value);
            }

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

    // 使用栈来实现
    @Override
    public void dfs(V begin, Visitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);

        if (beginVertex == null) {
            return;
        }

        Set<Vertex<V, E>> lisVertex = new HashSet<>();
        Stack<Vertex<V, E>> vertexStack = new Stack<>();
        vertexStack.push(beginVertex);

        if (visitor != null) {
            visitor.visit(beginVertex.value);
        }else {
            System.out.println(beginVertex);
        }


        lisVertex.add(beginVertex);

        while (!vertexStack.empty()) {

            Vertex<V, E> vertex = vertexStack.pop();

            for (Edge<V, E> outEdge: vertex.outEdges) {
                if (!lisVertex.contains(outEdge.to)) {
                    vertexStack.push(vertex);
                    vertexStack.push(outEdge.to);
                    lisVertex.add(outEdge.to);

                    if (visitor != null) {
                        visitor.visit(outEdge.to.value);
                    }else {
                        System.out.println(outEdge.to);
                    }

                    // 找到其中一个就break
                    break;
                }
            }
        }

    }


    public void dfsV2(V begin, Visitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);

        if (beginVertex == null) {
            return;
        }
        Set<Vertex<V, E>> lisVertex = new HashSet<>();
        dfs(beginVertex, lisVertex, visitor);
    }

    public void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> lisVertex, Visitor<V> visitor) {
        if (visitor != null) {
            visitor.visit(vertex.value);
        }else {
            System.out.println(vertex);
        }

        lisVertex.add(vertex);
        Set<Edge<V, E>> outEdges = vertex.outEdges;
        outEdges.forEach(outEdge -> {
            if (!lisVertex.contains(outEdge.to)) {
                dfs(outEdge.to, lisVertex, visitor);
            }
        });
    }

    @Override
    public List<V> topologicalSort() {

        List<V> list = new ArrayList<>();

        if (vertices.isEmpty()) {
            return null;
        }

        Queue<Vertex<V, E>> queue = new LinkedList<>();

        Map<V, Integer> map = new HashMap<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int size = vertex.inEdges.size();
            if (size == 0) {
                queue.offer(vertex);
            }
            map.put(v, size);
        });

        while (!queue.isEmpty()) {
            // 找到为0的添加到list中
            Vertex<V, E> poll = queue.poll();
            list.add(poll.value);

            for (Edge<V, E> edge : poll.outEdges) {
                Vertex<V, E> to = edge.to;

                Integer integer = map.get(to.value);
                if (integer - 1 == 0) {
                    queue.offer(to);
                }
                map.replace(to.value, integer - 1);
            }
        }

        return list;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return kruskal();
    }

    private Set<EdgeInfo<V, E>> prim() {
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();

        if (!vertices.values().iterator().hasNext()) {
            return edgeInfos;
        }
        Set<Vertex<V, E>> addVertex = new HashSet<>();
        Vertex<V, E> vertex = vertices.values().iterator().next();
        addVertex.add(vertex);
        BinaryHeap<Edge<V, E>> heap = new BinaryHeap<Edge<V, E>>(vertex.outEdges, edgeComparator);

        // 边是顶点数减1
        int edgeSize = vertices.size() - 1;
        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            // 最小的边
            Edge<V, E> edge = heap.remove();
            if (addVertex.contains(edge.to)) {
                continue;
            }
            edgeInfos.add(edge.edgeInfo());
            addVertex.add(edge.to);
            heap.addAll(edge.to.outEdges);
        }

        return edgeInfos;
    }

    private final Comparator<Edge<V, E>> edgeComparator = new Comparator<Edge<V, E>>() {
        @Override
        public int compare(Edge<V, E> o1, Edge<V, E> o2) {
            return -weightManager.compare(o1.weight, o2.weight);
        }
    };

    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = vertices.size() - 1;

        if (edgeSize == -1) {
            return null;
        }

        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        // 存储的是顶点
        UnionFind<Vertex<V, E>> uf = new UnionFind<>();

        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });

        BinaryHeap<Edge<V, E>> heap = new BinaryHeap<Edge<V, E>>(edges, edgeComparator);

        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            // 最小的边
            Edge<V, E> edge = heap.remove();
            // 使用并查集
            // 如果不是一个顶点就可以选
            if (!uf.isSame(edge.to, edge.from)) {
                edgeInfos.add(edge.edgeInfo());
                uf.union(edge.to, edge.from);
            }
        }

        return edgeInfos;
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

    private static class Edge<V, E>{
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        private EdgeInfo<V, E> edgeInfo() {
            return new EdgeInfo<>(from.value, to.value, weight);
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
