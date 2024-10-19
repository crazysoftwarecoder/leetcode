package org.example.algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    public String bfs(Node root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return sb.toString();
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (sb.length()>0) {
                sb.append(",");
            }
            sb.append(node.getData());
            if (node.getLeft()!=null) queue.offer(node.getLeft());
            if (node.getRight()!=null) queue.offer(node.getRight());
        }

        return sb.toString();
    }
}
