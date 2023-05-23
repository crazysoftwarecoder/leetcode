package org.example.algorithms.searching;

import java.util.List;

public class LinearSearch implements Search {

    public int search(List<Integer> arr, Integer search) {
        if (arr == null) return -1;
        for (int i=0;i<arr.size();i++) {
            if (arr.get(i).equals(search)) {
                return i;
            }
        }
        return -1;
    }
}
