package org.example.algorithms.graph;

import java.util.*;

class Graph {

    Map<String, List<Edge>> edges = new HashMap<>();

    public void addEdge(String source, String destination, int weight) {
        edges.putIfAbsent(source, new ArrayList<>());
        edges.putIfAbsent(destination, new ArrayList<>());
        edges.get(source).add(new Edge(destination, weight));
    }

    public List<Edge> neighbors(String nodeName) {
        return edges.get(nodeName);
    }
}

class TraversingNode {
    String name;
    int distance;

    public TraversingNode(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "TraversingNode{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }
}

class Edge {
    String destination;
    int weight;

    public Edge(String destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class Djikstra {

    public Map<String,Integer> djikstra(Graph graph, String source) {
        Map<String, Integer> distance = new HashMap<>();
        Set<String> visitedNodes = new HashSet<>();
        Queue<TraversingNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        for (String node : graph.edges.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
        }

        distance.put(source, 0);
        queue.add(new TraversingNode(source, 0));

        while (!queue.isEmpty()) {
            var node = queue.poll();

            if (visitedNodes.contains(node.name)) continue;
            visitedNodes.add(node.name); // mark visited

            for (Edge edge : graph.neighbors(node.name)) {
                if (visitedNodes.contains(edge.destination)) continue; // skip if visited

                // calc dist
                var dist = node.distance + edge.weight;
                if (dist < distance.get(edge.destination)) {
                    distance.put(edge.destination, dist);
                    queue.add(new TraversingNode(edge.destination, dist));
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 1);

        Map<String, Integer> distances = new Djikstra().djikstra(graph, "A");
        for (String name : distances.keySet()) {
            System.out.println(name + "->" + distances.get(name));
        }
    }
}