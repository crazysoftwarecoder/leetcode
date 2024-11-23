package org.example.algorithms.calendar;

public class Calendar1 {

    private static class SegTree {
        int startTime;
        int endTime;
        SegTree left;
        SegTree right;

        public SegTree(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    private SegTree root;

    public boolean book(int startTime, int endTime) {
        if (root == null) {
            root = new SegTree(startTime, endTime);
            return true;
        }

        return updateSegTree(root, startTime, endTime);
    }

    private boolean updateSegTree(SegTree node, int startTime, int endTime) {
        if (startTime <= node.startTime && endTime <= node.startTime) {
            if (node.left == null) {
                node.left = new SegTree(startTime, endTime);
                return true;
            }
            return updateSegTree(node.left, startTime, endTime);
        } else if (endTime >= node.endTime && startTime >= node.endTime) {
            if (node.right == null) {
                node.right = new SegTree(startTime, endTime);
                return true;
            }
            return updateSegTree(node.right, startTime, endTime);
        }

        return false;
    }

    public static void main(String[] args) {
        var obj = new Calendar1();
        System.out.println(obj.book(10,20));
        System.out.println(obj.book(22,25));
        System.out.println(obj.book(21,23));
    }
}
