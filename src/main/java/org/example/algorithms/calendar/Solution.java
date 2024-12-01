package org.example.algorithms.calendar;

import java.util.*;


import java.util.*;

class Solution {
    public static int[][] mergeIntervals(int[][] intervals) {
        if (intervals.length == 1) return intervals;

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        int resPtr = 1;
        // [1, 5], [3, 7], [4, 6], [6, 8]
        for (int i=1;i<intervals.length;i++) {
            var currInterval = intervals[i];
            var prevInterval = res.get(resPtr-1);
            if (currInterval[0] <= prevInterval[0]) {
                if (currInterval[1] <= prevInterval[1]) continue; // prev interval consumes currInterval
                prevInterval[1] = currInterval[1]; // set prev last time to curr's last time
                res.set(resPtr-1, prevInterval);
            } else {
                res.add(currInterval); // non overlapping interval.
                resPtr++;
            }
        }

        intervals = new int[res.size()][2];
        for (int i=0;i<res.size();i++) {
            intervals[i] = res.get(i);
        }

        return intervals;
    }

    public static void main(String[] args) {
        var intervals = mergeIntervals(new int[][]{new int[]{1,5}, new int[]{3,7}, new int[]{4,6}});
        for (int[] interval : intervals) {
            System.out.println(interval[0] + " " + interval[1]);
        }
    }
}
