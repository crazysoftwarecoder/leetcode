package org.example.algorithms.basic;

import java.util.Arrays;

public class FibonacciSeriesTopDown {

    public int[] fibonacciSeries(int n) {
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        _fibonacciSeries(n , memo);
        return memo;
    }

    private int _fibonacciSeries(int n, int[] memo) {
        if (memo[n] == -1) {
            if (n < 2) {
                memo[n] = n;
            }
            else {
                memo[n] = _fibonacciSeries(n-1, memo) + _fibonacciSeries(n-2, memo);
            }
        }

        return memo[n];
    }
}
