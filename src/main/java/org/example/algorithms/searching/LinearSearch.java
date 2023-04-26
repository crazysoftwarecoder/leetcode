package org.example.algorithms.searching;

import java.util.List;

public class LinearSearch<T> {

    public int search(List<T> arr, T search) {
        if (arr == null) return -1;
        for (int i=0;i<arr.size();i++) {
            if (arr.get(i).equals(search)) {
                return i;
            }
        }
        return -1;
    }
}
