package org.example.algorithms.tree;

enum TraversalMode {
    PREORDER,
    INORDER,
    POSTORDER
}

public class DepthFirstSearch {

    public String dfsPreOrder(Node root) {
        var res = new StringBuilder();
        _dfs(root, res, TraversalMode.PREORDER);
        return res.toString();
    }

    public String dfsInOrder(Node root) {
        var res = new StringBuilder();
        _dfs(root, res, TraversalMode.INORDER);
        return res.toString();
    }

    public String dfsPostOrder(Node root) {
        var res = new StringBuilder();
        _dfs(root, res, TraversalMode.POSTORDER);
        return res.toString();
    }

    private void _dfs(Node root, StringBuilder sb, TraversalMode mode) {
        if (root == null) {
            return;
        }

        if (mode == TraversalMode.PREORDER) {
            storeData(sb, root);
        }

        _dfs(root.getLeft(), sb, mode);

        if (mode == TraversalMode.INORDER) {
            storeData(sb, root);
        }

        _dfs(root.getRight(), sb, mode);

        if (mode == TraversalMode.POSTORDER) {
            storeData(sb, root);
        }
    }

    private void storeData(StringBuilder sb, Node root) {
        if (sb.length() > 0) {
            sb.append(",");
        }
        sb.append(root.getData());
    }
}
