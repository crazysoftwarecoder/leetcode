package org.example.algorithms.graph;

import java.util.*;

public class Djikstra {

    // Edge class representing an edge from a source node to a target node with a given weight
    record Edge(int targetNode, int weight) {}

    // Dijkstra's algorithm implementation
    public static Map<Integer, Integer> dijkstra(Map<Integer, List<Edge>> graph, int source) {
        Map<Integer, Integer> distMap = new HashMap<>();

        for (Map.Entry<Integer, List<Edge>> entry : graph.entrySet()) {
            distMap.put(entry.getKey(), Integer.MAX_VALUE);
            for (Edge edge : entry.getValue()) {
                distMap.put(edge.targetNode, Integer.MAX_VALUE);
            }
        }

        distMap.put(source, 0);

        var pq = new PriorityQueue<Integer>(Comparator.comparingInt(distMap::get));
        pq.add(source);

        while (!pq.isEmpty()) {
            Integer currNode = pq.poll();

            if (!graph.containsKey(currNode)) {
                continue;
            }

            for (Edge edge : graph.getOrDefault(currNode, Collections.emptyList())) {
                var target = edge.targetNode();
                var weight = edge.weight();

                var currDist = distMap.get(currNode) + weight;

                if (currDist < distMap.get(target)) {
                    distMap.put(target, currDist);
                    pq.offer(target);
                }
            }
        }

        return distMap;
    }

    // Example usage
    public static void main(String[] args) {
        // Create a graph represented as an adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();

        // Add edges to the graph (example graph)
        graph.put(0, Arrays.asList(new Edge(1, 4), new Edge(2, 1)));
        graph.put(1, Arrays.asList(new Edge(3, 1)));
        graph.put(2, Arrays.asList(new Edge(1, 2), new Edge(3, 5)));
        graph.put(3, Collections.emptyList());

        int source = 0;      // Starting node
        int numNodes = 4;    // Total number of nodes in the graph

        // Run Dijkstra's algorithm
        Map<Integer, Integer> distMap = dijkstra(graph, source);

        // Print the shortest distances from the source to each node
        for (Map.Entry<Integer, Integer> entry : distMap.entrySet()) {
            System.out.println("Distance from node " + source + " to node " + entry.getKey() + " is " + entry.getValue());
        }
    }
}