package org.example.dynamicprogramming;

// Given an integer n, return an array ans of length n + 1 such that for
// each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
public class CountingBits {

    public int[] countingBits(int n) {
        int[] res = new int[n+1];
        for (int i=1;i<=n;i++) {
            res[i] = res[i>>1] + (i & 1);
        }
        return res;
    }
}
