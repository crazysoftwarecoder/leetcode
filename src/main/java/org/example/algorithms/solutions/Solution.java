package org.example.algorithms.solutions;


import java.util.HashMap;

import java.util.*;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] leftMultArr = new int[nums.length];
        int[] rightMultArr = new int[nums.length];
        int[] res = new int[nums.length];

        for (int i=0;i<nums.length;i++) {
            int leftIndex = i;
            int rightIndex = nums.length-1-i;

            if (leftIndex == 0) {
                leftMultArr[leftIndex] = nums[leftIndex];
            }
            else {
                leftMultArr[leftIndex] = leftMultArr[leftIndex-1] * nums[leftIndex];
            }

            if (rightIndex == nums.length-1) {
                rightMultArr[rightIndex] = nums[rightIndex];
            }
            else {
                rightMultArr[rightIndex] = rightMultArr[rightIndex+1] * nums[rightIndex];
            }
        }

        res[0] = rightMultArr[1];
        res[res.length-1] = leftMultArr[res.length-2];
        for (int i=1;i<res.length-1;i++) {
            res[i] = leftMultArr[i-1] * rightMultArr[i+1];
        }
        return res;
    }

    public static void main(String[] args) {
        var soln = new Solution();
        System.out.println(Arrays.toString(soln.productExceptSelf(new int[] {1,2,3,4})));
    }
}