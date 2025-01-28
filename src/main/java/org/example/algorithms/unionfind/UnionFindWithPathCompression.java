package org.example.algorithms.unionfind;

import java.util.Arrays;

public class UnionFindWithPathCompression {

    int[] arr;

    public UnionFindWithPathCompression(int n) {
        arr = new int[n];
        for (int i=0;i<n;i++) {
            arr[i] = i;
        }
    }

    public int root(int i) {
        while (arr[i] != i) {
            arr[i] = arr[arr[i]];
            i = arr[i];
        }

        return i;
    }

    public void union(int a, int b) {
        int rootA = root(a);
        int rootB = root(b);
        arr[rootB] = rootA;
    }

    public boolean connected(int a, int b) {
        return root(a) == root(b);
    }

    public static void main(String[] args) {
        UnionFindWithPathCompression uf = new UnionFindWithPathCompression(10);

        System.out.println("Initial State: " + Arrays.toString(uf.arr));

        // Test 1: Union and connection checks
        System.out.println("--- Test 1: Union(1, 3) ---");
        uf.union(1, 3);
        System.out.println("Connected(1, 3): " + uf.connected(1, 3)); // True
        System.out.println("State after union(1, 3): " + Arrays.toString(uf.arr));

        System.out.println("--- Test 2: Union(3, 5) ---");
        uf.union(3, 5);
        System.out.println("Connected(1, 5): " + uf.connected(1, 5)); // True
        System.out.println("State after union(3, 5): " + Arrays.toString(uf.arr));

        System.out.println("--- Test 3: Union(5, 7) ---");
        uf.union(5, 7);
        System.out.println("Connected(1, 7): " + uf.connected(1, 7)); // True
        System.out.println("State after union(5, 7): " + Arrays.toString(uf.arr));

        // Test 4: Check connectivity for unrelated nodes
        System.out.println("--- Test 4: Check connectivity of unrelated nodes ---");
        System.out.println("Connected(1, 8): " + uf.connected(1, 8)); // False
        System.out.println("Connected(2, 4): " + uf.connected(2, 4)); // False

        // Test 5: Connecting unrelated components
        System.out.println("--- Test 5: Union(0, 2) ---");
        uf.union(0, 2);
        System.out.println("Connected(0, 2): " + uf.connected(0, 2)); // True
        System.out.println("State after union(0, 2): " + Arrays.toString(uf.arr));

        System.out.println("--- Test 6: Union(2, 4) ---");
        uf.union(2, 4);
        System.out.println("Connected(0, 4): " + uf.connected(0, 4)); // True
        System.out.println("State after union(2, 4): " + Arrays.toString(uf.arr));

        // Test 7: Check transitivity
        System.out.println("--- Test 7: Transitivity test ---");
        System.out.println("Connected(1, 7): " + uf.connected(1, 7)); // True
        System.out.println("Connected(0, 4): " + uf.connected(0, 4)); // True
        System.out.println("Connected(1, 4): " + uf.connected(1, 4)); // False

        // Test 8: Complex union operations
        System.out.println("--- Test 8: Union(1, 4) ---");
        uf.union(1, 4);
        System.out.println("Connected(1, 4): " + uf.connected(1, 4)); // True
        System.out.println("State after union(1, 4): " + Arrays.toString(uf.arr));

        System.out.println("--- Test 9: Connectivity checks after all unions ---");
        System.out.println("Connected(0, 7): " + uf.connected(0, 7)); // True
        System.out.println("Connected(8, 9): " + uf.connected(8, 9)); // False

        // Final State
        System.out.println("--- Final State ---");
        System.out.println(Arrays.toString(uf.arr));
    }
}
