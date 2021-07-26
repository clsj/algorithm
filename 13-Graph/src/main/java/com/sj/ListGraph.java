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

    public Map<V, E> shortestPath1(V begin) {
        Map<V, E> shortestPaths = new HashMap<>();

        // 存储正在计算的路径
        Map<Vertex<V, E>, E> paths = new HashMap<>();

        Vertex<V, E> vertex = vertices.get(begin);
        vertex.outEdges.forEach(edge -> {
            paths.put(edge.to, edge.weight);
        });

        while (!paths.isEmpty()) {
            // 找到最短路径
            Map.Entry<Vertex<V, E>, E> minPath = getMinPath(paths);
            paths.remove(minPath.getKey());
            // 存储在shortestPaths中
            shortestPaths.put(minPath.getKey().value, minPath.getValue());

            Vertex<V, E> vertexKey = minPath.getKey();
            E weight = minPath.getValue();
            vertexKey.outEdges.forEach(edge -> {
                // 找到to
                Vertex<V, E> to = edge.to;
                if (shortestPaths.containsKey(to.value) ){
                    return;
                }
                // 查看paths中是否有to，如果没有添加，如果有就比较存最小的
                if (paths.containsKey(to)) {
                    // 存在判断
                    E oldWeight = paths.get(to);
                    E newWeight = weightManager.add(weight, edge.weight);

                    // newWeight小 更新weight
                    if (weightManager.compare(oldWeight, newWeight) > 0) {
                        paths.replace(to, newWeight);
                    }
                }else {
                    paths.put(to, edge.weight);
                }
            });
        }

        shortestPaths.remove(begin);

        return shortestPaths;
    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPathBellmanFord(V begin) {
        return bellmanFord(begin);
    }

    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {

        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }

        Map<V, PathInfo<V, E>> shortestPaths = new HashMap<>();
        // 对A到A为0操作
        shortestPaths.put(begin, new PathInfo<>(weightManager.zero()));
        int count = vertices.size() - 1;

        for (int i = 0; i < count; i++) {
            for (Edge<V, E> edge : edges) {
                // 查询from的pathInfo 如果不存在就不进行松弛操作
                // A到A是0
                PathInfo<V, E> pathInfo = shortestPaths.get(edge.from.value);
                if (pathInfo != null) {
                    relaxForBellmanFord(edge, pathInfo, shortestPaths);
                }
            }
        }

        for (Edge<V, E> edge : edges) {
            // 查询from的pathInfo 如果不存在就不进行松弛操作
            // A到A是0
            PathInfo<V, E> pathInfo = shortestPaths.get(edge.from.value);
            if (pathInfo != null) {
                boolean flag = relaxForBellmanFord(edge, pathInfo, shortestPaths);
                // 成功的话 说明有负权环
                if (flag) {
                    //
                    System.out.println("有负权环");
                }
            }
        }

        shortestPaths.remove(begin);

        return shortestPaths;
    }

    // 对edge的to进行松弛
    private boolean relaxForBellmanFord(Edge<V, E> edge, PathInfo<V, E> fromPathInfo, Map<V, PathInfo<V, E>> paths) {

        Vertex<V, E> to = edge.to;

        // 根据from的weight判断
        E weight = fromPathInfo.weight;

        // 查看paths中是否有to，如果没有添加，如果有就比较存最小的
        if (paths.containsKey(to.value)) {

            PathInfo<V, E> vePathInfo = paths.get(to.value);

            // 存在判断
            E oldWeight = vePathInfo.weight;
            E newWeight = weightManager.add(weight, edge.weight);

            // newWeight小 更新weight
            if (weightManager.compare(oldWeight, newWeight) > 0) {
                vePathInfo.weight = newWeight;
                // 如果是老的 先清空
                vePathInfo.edgeInfos.clear();
                // 将from的路径加进去
                vePathInfo.edgeInfos.addAll(fromPathInfo.edgeInfos);
                // 加上新的路径
                vePathInfo.edgeInfos.add(edge.edgeInfo());
            }else {
                return false;
            }
        }else {
            // 如果不存在 初始化 不要忘了weight是总的，还有edgeInfo
            PathInfo<V, E> info = new PathInfo<>(weightManager.add(weight, edge.weight));
            info.edgeInfos.add(edge.edgeInfo());
            paths.put(to.value, info);
        }

        return true;
    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPathDijkstra(V begin) {
        return dijkstraV2(begin);
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> shortestFloyed() {

        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();

        for (Edge<V, E> edge : edges) {

            // 1. 先把paths中V到V的pathInfo取出来，如果没有加添加
            Map<V, PathInfo<V, E>> infoMap = paths.get(edge.from.value);

            if (infoMap == null) {
                infoMap = new HashMap<>();
                paths.put(edge.from.value, infoMap);
            }

            PathInfo<V, E> pathInfo = new PathInfo<>(edge.weight);
            pathInfo.edgeInfos.add(edge.edgeInfo());
            infoMap.put(edge.to.value, pathInfo);
        }

        vertices.forEach((V v2, Vertex<V, E> vertex2) -> {
            vertices.forEach((V v1, Vertex<V, E> vertex1) -> {
                vertices.forEach((V v3, Vertex<V, E> vertex3) -> {

                    if (v1.equals(v2) || v1.equals(v3) || v2.equals(v3)) {
                        return;
                    }

                    PathInfo<V, E> pathInfo12 = getPathInfo(v1, v2, paths);
                    if (pathInfo12 == null) {
                        return;
                    }

                    PathInfo<V, E> pathInfo23 = getPathInfo(v2, v3, paths);
                    if (pathInfo23 == null) {
                        return;
                    }

                    PathInfo<V, E> pathInfo13 = getPathInfo(v1, v3, paths);

                    // 1->3 为 1->2 + 2->3
                    E newWeight = weightManager.add(pathInfo12.weight, pathInfo23.weight);

                    // pathInfo13存在 而且权重比新的小或者等于 没必要更新
                    if (pathInfo13 != null && weightManager.compare(pathInfo13.weight, newWeight) <= 0) {
                        return;
                    }

                    if (pathInfo13 == null) {
                        pathInfo13 = new PathInfo<V, E>();
                        paths.get(v1).put(v3, pathInfo13);
                    }else {
                        pathInfo13.edgeInfos.clear();
                    }

                    pathInfo13.weight = newWeight;
                    pathInfo13.edgeInfos.addAll(pathInfo12.edgeInfos);
                    pathInfo13.edgeInfos.addAll(pathInfo23.edgeInfos);
                });
            });
        });



        return paths;
    }

    private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V,E>>> paths) {

        Map<V, PathInfo<V, E>> map = paths.get(from);
        return map == null ? null : map.get(to);
    }

    private Map<V, PathInfo<V, E>> dijkstraV2(V begin) {

        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }

        Map<V, PathInfo<V, E>> shortestPaths = new HashMap<>();

        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();

        paths.put(beginVertex, new PathInfo<>(weightManager.zero()));

        while (!paths.isEmpty()) {
            // 找到最短路径
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minPath = getMinPath2(paths);
            paths.remove(minPath.getKey());
            // 存储在shortestPaths中
            shortestPaths.put(minPath.getKey().value, minPath.getValue());

            Vertex<V, E> vertexKey = minPath.getKey();

            vertexKey.outEdges.forEach(edge -> {
                if (shortestPaths.containsKey(edge.to.value) ){
                    return;
                }
                relaxForDijkstra(edge, shortestPaths.get(edge.from.value), paths);
            });
        }

        shortestPaths.remove(begin);

        return shortestPaths;
    }


    // dijkstra求最短路径算法
    private Map<V, PathInfo<V, E>> dijkstra(V begin) {
        Map<V, PathInfo<V, E>> shortestPaths = new HashMap<>();

        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
        Vertex<V, E> vertex = vertices.get(begin);
        vertex.outEdges.forEach(edge -> {
            PathInfo<V, E> info = new PathInfo<>(edge.weight);
            info.edgeInfos.add(edge.edgeInfo());
            paths.put(edge.to, info);
        });

        while (!paths.isEmpty()) {
            // 找到最短路径
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minPath = getMinPath2(paths);
            paths.remove(minPath.getKey());
            // 存储在shortestPaths中
            shortestPaths.put(minPath.getKey().value, minPath.getValue());

            Vertex<V, E> vertexKey = minPath.getKey();

            vertexKey.outEdges.forEach(edge -> {
                if (shortestPaths.containsKey(edge.to.value) ){
                    return;
                }
                relaxForDijkstra(edge, shortestPaths.get(edge.from.value), paths);
            });
        }

        shortestPaths.remove(begin);

        return shortestPaths;
    }

    private void relaxForDijkstra(Edge<V, E> edge, PathInfo<V, E> fromPathInfo, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        // 找到to
        Vertex<V, E> to = edge.to;

        E weight = fromPathInfo.weight;

        // 查看paths中是否有to，如果没有添加，如果有就比较存最小的
        if (paths.containsKey(to)) {

            PathInfo<V, E> vePathInfo = paths.get(to);

            // 存在判断
            E oldWeight = vePathInfo.weight;
            E newWeight = weightManager.add(weight, edge.weight);

            // newWeight小 更新weight
            if (weightManager.compare(oldWeight, newWeight) > 0) {
                vePathInfo.weight = newWeight;
                // 如果是老的 先清空
                vePathInfo.edgeInfos.clear();
                // 将from的路径加进去
                vePathInfo.edgeInfos.addAll(fromPathInfo.edgeInfos);
                // 加上新的路径
                vePathInfo.edgeInfos.add(edge.edgeInfo());
            }
        }else {
            // 如果不存在 初始化 不要忘了weight是总的，还有edgeInfo
            PathInfo<V, E> info = new PathInfo<>(weightManager.add(weight, edge.weight));
            info.edgeInfos.add(edge.edgeInfo());
            paths.put(to, info);
        }
    }


    // 获取paths中的最短路径
    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath2(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> min = null;
        if (iterator.hasNext()) {
            min = iterator.next();
        }

        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> next = iterator.next();

            if (weightManager.compare(min.getValue().weight, next.getValue().weight) > 0) {
                min = next;
            }
        }
        return min;
    }

    // 获取paths中的最短路径
    private Map.Entry<Vertex<V, E>, E> getMinPath(Map<Vertex<V, E>, E> paths) {
        Iterator<Map.Entry<Vertex<V, E>, E>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, E> min = null;
        if (iterator.hasNext()) {
            min = iterator.next();
        }

        while (iterator.hasNext()) {
            Map.Entry<Vertex<V, E>, E> next = iterator.next();

            if (weightManager.compare(min.getValue(), next.getValue()) > 0) {
                min = next;
            }
        }
        return min;
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
