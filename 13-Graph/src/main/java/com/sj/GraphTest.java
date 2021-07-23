package com.sj;

public class GraphTest {

    public static void test01() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);
        graph.bfs("V2", new ListGraph.Visitor<String, Integer>() {
            @Override
            public void visit(ListGraph.Vertex<String, Integer> vertex) {
                System.out.println(vertex.value);
            }
        });
    }

    public static void main(String[] args) {



    }

}
