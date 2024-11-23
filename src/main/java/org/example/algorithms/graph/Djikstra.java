package org.example.algorithms.graph;

import java.util.*;

public class Djikstra {

    // Edge class representing an edge from a source node to a target node with a given weight
    static class Edge {
        int targetNode;
        int weight;

        public Edge(int targetNode, int weight) {
            this.targetNode = targetNode;
            this.weight = weight;
        }
    }

    // Dijkstra's algorithm implementation
    public static int[] dijkstra(Map<Integer, List<Edge>> graph, int source, int numNodes) {
        int[] dist = new int[numNodes];
        for (int i=0;i<dist.length;i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0; // distance from source to source is zero.

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> dist[node]));
        pq.add(source);

        while (!pq.isEmpty()) {
            int currentNode = pq.poll();

            if (!graph.containsKey(currentNode)) {
                continue;
            }

            for (var edge : graph.get(currentNode)) {
                var targetNode = edge.targetNode;
                var weight = edge.weight;

                var newDist = dist[currentNode] + weight;

                if (newDist < dist[targetNode]) {
                    dist[targetNode] = newDist;
                    pq.add(targetNode);
                }
            }
        }

        return dist;
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
        int[] distances = dijkstra(graph, source, numNodes);

        // Print the shortest distances from the source to each node
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Distance from node " + source + " to node " + i + " is " + distances[i]);
        }
    }
}