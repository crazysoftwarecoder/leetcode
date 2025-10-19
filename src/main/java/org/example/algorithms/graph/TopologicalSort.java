package org.example.algorithms.graph;

import java.util.*;

public class TopologicalSort<T> {

    public static class Graph<T> {
        private final Map<T, List<T>> map;

        public Graph() {
            this.map = new HashMap<>();
        }

        public void addEdge(T a, T b) {
            map.computeIfAbsent(a, k -> new ArrayList<T>()).add(b);
            map.computeIfAbsent(b, k -> new ArrayList<T>());
        }

        public List<T> getNeigbours(T a) {
            return map.get(a);
        }

        public List<T> getVertices() {
            return map.keySet().stream().toList();
        }
    }

    public List<T> topoSort(Graph<T> graph) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        for (var vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                dfs(vertex, graph, stack, visited);
            }
        }

        List<T> res = new ArrayList<>();

        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

    private void dfs(T vertex, Graph<T> graph, Stack<T> stack, Set<T> visited) {
        visited.add(vertex);
        if (graph.getNeigbours(vertex) == null) return;

        for (T neighbour : graph.getNeigbours(vertex)) {
            if (!visited.contains(neighbour)) {
                dfs(neighbour, graph, stack, visited);
            }
        }

        stack.push(vertex);
    }
}
