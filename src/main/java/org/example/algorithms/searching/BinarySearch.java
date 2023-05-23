package org.example.algorithms.searching;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch implements Search {

    public static enum TYPE { ITERATIVE, RECURSIVE };

    private TYPE type;

    public BinarySearch(TYPE type) {
        this.type = type;
    }

    public int search(List<Integer> array, Integer search) {
        if (array==null) return -1;
        if (search == null) return array.indexOf(null);
        Collections.sort(array);
        if (this.type == TYPE.ITERATIVE) {
            return _binarySearchI(array, search);
        }
        else {
            return _binarySearchR(array, search);
        }
    }

    private int _binarySearchI(List<Integer> arr, Integer search) {
        int lo = 0;
        int hi = arr.size();

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (arr.get(mid) == search) {
                return mid;
            }
            else if (arr.get(mid) > search) {
                lo = mid+1;
            }
            else {
                hi = mid-1;
            }
        }

        return -1;
    }

    private int _binarySearchR(List<Integer> arr, Integer search) {
        int lo = 0;
        int hi = arr.size();
        return __binaryhSearchR(arr, search, lo, hi);
    }

    private int __binaryhSearchR(List<Integer> arr, int search, int lo, int hi) {
        if (lo > hi) return -1;

        int mid = (lo + hi) >>> 1;
        if (arr.get(mid) == search) {
            return mid;
        }
        else if (arr.get(mid) > search) {
            return __binaryhSearchR(arr, search, mid+1, hi);
        }
        else {
            return __binaryhSearchR(arr, search, lo, mid-1);
        }
    }
}
