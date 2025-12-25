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
                Set<T> inDFSStack = new HashSet<>();
                dfs(vertex, graph, stack, inDFSStack, visited);
            }
        }

        List<T> res = new ArrayList<>();

        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

    public List<T> topoSortBFS(Graph<T> graph) {
        Map<T, Integer> indegree = new HashMap<>();

        for (T vertex : graph.getVertices()) {
            for (T neighbour : graph.getNeigbours(vertex)) {
                indegree.put(neighbour, indegree.getOrDefault(neighbour, 0) + 1);
            }
            if (!indegree.containsKey(vertex)) {
                indegree.put(vertex, 0);
            }
        }

        Queue<T> q = new LinkedList<>();
        for (T vertex : graph.getVertices()) {
            if (indegree.get(vertex) == 0) {
                q.offer(vertex);
            }
        }

        List<T> sorted = new ArrayList<>();

        while (!q.isEmpty()) {
            T vertex = q.poll();

            sorted.add(vertex);

            for (T neighbour : graph.getNeigbours(vertex)) {
                if (indegree.get(neighbour) == 0) continue;
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if (indegree.get(neighbour) == 0) {
                    q.offer(neighbour);
                }
            }
        }

        return sorted;
    }

    private void dfs(T vertex, Graph<T> graph, Stack<T> stack, Set<T> inDFSStack, Set<T> visited) {
        visited.add(vertex);
        inDFSStack.add(vertex);
        if (graph.getNeigbours(vertex) == null) return;

        for (T neighbour : graph.getNeigbours(vertex)) {
            if (!visited.contains(neighbour)) {
                dfs(neighbour, graph, stack, inDFSStack, visited);
            } else if (inDFSStack.contains(neighbour)) {
                throw new IllegalArgumentException("Input graph has a cycle");
            }
        }

        stack.push(vertex);
    }
}
