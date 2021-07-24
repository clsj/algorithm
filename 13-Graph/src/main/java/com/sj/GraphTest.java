package com.sj;

import java.util.Set;

public class GraphTest {

    static WeightManager<Double> weightManager = new WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }

        @Override
        public Double zero() {
            return 0.0;
        }
    };

    public static void test01() {
        ListGraph<String, Integer> graph = new ListGraph<>(null);
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);
        graph.bfs("V2", new AbstractGraph.Visitor<String>() {
            @Override
            public void visit(String value) {
                System.out.println(value);
            }
        });
    }

    public static void main(String[] args) {

        AbstractGraph<Object, Double> abstractGraph = undirectedGraph(Data.MST_01);

        Set<AbstractGraph.EdgeInfo<Object, Double>> mst = abstractGraph.mst();

        for (AbstractGraph.EdgeInfo<Object, Double> edgeInfo : mst) {
            System.out.println(edgeInfo);
        }

    }

    /**
     * 有向图
     */
    private static AbstractGraph<Object, Double> directedGraph(Object[][] data) {
        AbstractGraph<Object, Double> abstractGraph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                abstractGraph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                abstractGraph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                abstractGraph.addEdge(edge[0], edge[1], weight);
            }
        }
        return abstractGraph;
    }

    /**
     * 无向图
     */
    private static AbstractGraph<Object, Double> undirectedGraph(Object[][] data) {
        AbstractGraph<Object, Double> abstractGraph = new ListGraph<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                abstractGraph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                abstractGraph.addEdge(edge[0], edge[1]);
                abstractGraph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                abstractGraph.addEdge(edge[0], edge[1], weight);
                abstractGraph.addEdge(edge[1], edge[0], weight);
            }
        }
        return abstractGraph;
    }

}
