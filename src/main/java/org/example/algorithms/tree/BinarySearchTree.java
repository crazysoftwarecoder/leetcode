package org.example.algorithms.tree;

import java.util.Comparator;

public class BinarySearchTree {

    static class AVLTreeMap<K, V> {
        private static final class Node<K, V> {
            K key;
            V value;
            int height = 1;           // leaf height = 1
            Node<K, V> left, right;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private Node<K, V> root;
        private final Comparator<? super K> cmp;

        public AVLTreeMap() { this.cmp = null; }
        public AVLTreeMap(Comparator<? super K> cmp) { this.cmp = cmp; }

        /** Insert or update. Returns previous value if key existed, else null. */
        public V put(K key, V value) {
            if (key == null) throw new NullPointerException("key");
            Holder<V> replaced = new Holder<>();
            root = insert(root, key, value, replaced);
            return replaced.val;
        }

        /** Lookup (optional helper). */
        public V get(K key) {
            Node<K, V> n = root;
            while (n != null) {
                int c = compare(key, n.key);
                if (c < 0) n = n.left;
                else if (c > 0) n = n.right;
                else return n.value;
            }
            return null;
        }

        // ---------- internals ----------

        private static final class Holder<T> { T val; }

        private Node<K, V> insert(Node<K, V> node, K key, V value, Holder<V> replaced) {
            if (node == null) return new Node<>(key, value);

            int c = compare(key, node.key);
            if (c < 0) {
                node.left = insert(node.left, key, value, replaced);
            } else if (c > 0) {
                node.right = insert(node.right, key, value, replaced);
            } else {
                // key exists: update value, no rebalancing needed nor height refresh
                replaced.val = node.value;
                node.value = value;
                return node;
            }

            updateHeight(node);
            return rebalance(node, key);
        }

        private Node<K, V> rebalance(Node<K, V> node, K insertedKey) {
            int balance = balance(node);

            // Left heavy
            if (balance > 1) {
                if (compare(insertedKey, node.left.key) < 0) {
                    // LL
                    return rotateRight(node);
                } else {
                    // LR
                    node.left = rotateLeft(node.left);
                    return rotateRight(node);
                }
            }

            // Right heavy
            if (balance < -1) {
                if (compare(insertedKey, node.right.key) > 0) {
                    // RR
                    return rotateLeft(node);
                } else {
                    // RL
                    node.right = rotateRight(node.right);
                    return rotateLeft(node);
                }
            }

            return node; // already balanced
        }

        private Node<K, V> rotateRight(Node<K, V> y) {
            Node<K, V> x = y.left;
            Node<K, V> t2 = x.right;

            x.right = y;
            y.left = t2;

            updateHeight(y);
            updateHeight(x);
            return x;
        }

        private Node<K, V> rotateLeft(Node<K, V> y) {
            Node<K, V> x = y.right;
            Node<K, V> t2 = x.left;

            x.left = y;
            y.right = t2;

            updateHeight(y);
            updateHeight(x);
            return x;
        }

        private void updateHeight(Node<K, V> n) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
        }

        private int height(Node<K, V> n) {
            return (n == null) ? 0 : n.height;
        }

        private int balance(Node<K, V> n) {
            return (n == null) ? 0 : height(n.left) - height(n.right);
        }

        @SuppressWarnings("unchecked")
        private int compare(K a, K b) {
            if (cmp != null) return cmp.compare(a, b);
            // Requires K to be Comparable if no comparator provided
            return ((Comparable<? super K>) a).compareTo(b);
        }

        // Simple demo
        public static void main(String[] args) {
            AVLTreeMap<Integer, String> avl = new AVLTreeMap<>();
            avl.put(10, "a");
            avl.put(20, "b");
            avl.put(30, "c");
            avl.put(40, "d");
            avl.put(50, "e");
            avl.put(25, "f");  // triggers rotations along the way

            System.out.println(avl.get(25)); // "f"
            System.out.println(avl.get(15)); // null
        }
    }

    static class BinarySearchTreeUnbalanced {
        private static class Node {
            int value;
            Node left;
            Node right;

            Node(int value) {
                this.value = value;
                this.left = null;
                this.right = null;
            }
        }

        private Node root;

        public BinarySearchTreeUnbalanced() {
            this.root = null;
        }

        public void add(int value) {
            root = addRecursive(root, value);
        }

        private Node addRecursive(Node current, int value) {
            if (current == null) {
                return new Node(value);
            }

            if (value < current.value) {
                current.left = addRecursive(current.left, value);
            } else if (value > current.value) {
                current.right = addRecursive(current.right, value);
            }

            return current;
        }
        
        public boolean search(int value) {
            return searchRecursive(root, value);
        }
        
        private boolean searchRecursive(Node current, int value) {
            if (current == null) {
                return false;
            }
            
            if (value == current.value) {
                return true;
            }
            
            if (value < current.value) {
                return searchRecursive(current.left, value);
            } else {
                return searchRecursive(current.right, value);
            }
        }
    }
}

