package org.example.algorithms.union;

public class ConnectedFind {

    private int[] arr;

    public ConnectedFind(int n) {
        arr = new int[n];
        for (int i=0;i<n;i++) {
            arr[i] = i;
        }
    }

    public boolean isConnected(int p, int q) {
        int max = arr.length-1;
        if ( (p > max) || (q > max) )
            throw new IllegalArgumentException();
        return arr[p] == arr[q];
    }

    public void union(int p, int q) {
        int max = arr.length-1;
        if ( (p > max) || (q > max) )
            throw new IllegalArgumentException();

        int pid = arr[p];
        int qid = arr[q];
        for (int i=0;i<arr.length;i++) {
            if (arr[i] == pid) {
                arr[i] = qid;
            }
        }
    }
}
