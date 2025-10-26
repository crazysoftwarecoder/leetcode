package org.example.algorithms.atlassian;

import java.util.*;

public class OrgChart {

    public static class Node {

        private final String data;

        private Node parent;
        private final Node left;
        private final Node right;

        public String getData() {
            return data;
        }

        public Node(String data, Node left, Node right) {
            this.data = data;
            this.parent = null;
            this.left = left;
            this.right = right;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }

    public Node getCommonAncestor(Node root, List<String> employees) {
        List<Node> nodes = new ArrayList<>();
        dfs(root, nodes, new HashSet<String>(employees));

        Map<String, Integer> nodeFreqMap = new HashMap<>();

        List<Node> singlePathOfAnEmp = new ArrayList<>();
        boolean capturedSinglePath = false;
        for (Node node : nodes) {
            do {
                nodeFreqMap.put(node.data, nodeFreqMap.getOrDefault(node.data, 0) + 1);
                if (!capturedSinglePath) {
                    singlePathOfAnEmp.add(node);
                }
                node = node.parent;
            } while (node != null);
            capturedSinglePath = true;
        }

        for (Node node : singlePathOfAnEmp) {
            if (nodeFreqMap.get(node.data) == employees.size()) {
                return node;
            }
        }

        return null;

        // (size of tree) + (number of employees as input * height of tree)
        // (size of tree) + (number of employees as input * height of tree)
    }

    private void dfs(Node root, List<Node> res, Set<String> employees) {
        if (root == null) return;

        if (employees.contains(root.data)) {
            res.add(root);
            employees.remove(root.data);
        }

        dfs(root.left, res, employees);
        dfs(root.right, res, employees);
    }
}

    //            Company
    //          /          \
    //         HR          Engg
    //        /  \          /
    //       A    B         C

// B, 1, HR, 1, Company, 2
// C, 1, Engg, 1

