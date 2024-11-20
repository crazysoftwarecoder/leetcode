package org.example.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class DetectCycleInAGraph {

    private boolean _detectCycle(int source, int currentNode, List<List<Integer>> graph) {

        for (Integer child : graph.get(currentNode)) {
            if (child == source || child == currentNode) {
                return true;
            }
            if (_detectCycle(source, child, graph)) {
                return true;
            }
        }

        return false;
    }

    public boolean detectCycle(List<List<Integer>> graph) {
        // Go through all vertices.
        for (int i=0;i<graph.size();i++) {
            if (_detectCycle(i, i, graph)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var detectCycleInGraph = new DetectCycleInAGraph();
        List<List<Integer>> ab = new ArrayList<>();
        ab.add(new ArrayList<>());
        ab.add(new ArrayList<>());
        ab.add(new ArrayList<>());
        ab.add(new ArrayList<>());
        ab.get(0).add(1);
        ab.get(0).add(2);
        ab.get(1).add(2);
        ab.get(2).add(3);
        ab.get(3).add(1);
        System.out.println(detectCycleInGraph.detectCycle(ab));
    }
}
