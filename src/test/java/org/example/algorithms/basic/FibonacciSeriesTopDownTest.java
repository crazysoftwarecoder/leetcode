package org.example.algorithms.basic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FibonacciSeriesTopDownTest {

    @Test
    public void test() {
        FibonacciSeriesTopDown obj = new FibonacciSeriesTopDown();
        System.out.println(Arrays.toString(obj.fibonacciSeries(40)));
    }
}
