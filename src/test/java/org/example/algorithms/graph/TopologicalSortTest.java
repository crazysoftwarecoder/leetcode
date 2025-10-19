package org.example.algorithms.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TopologicalSortTest {

    @Test
    public void test() {
        var sorter = new TopologicalSort<Integer>();

        TopologicalSort.Graph<Integer> graph = new TopologicalSort.Graph<>();

        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        Assertions.assertEquals(sorter.topoSort(graph), List.of(5,4,2,3,1,0));
        Assertions.assertEquals(sorter.topoSortBFS(graph), List.of(4, 5, 2, 0, 3, 1));
    }
}
