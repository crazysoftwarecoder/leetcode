package org.example.algorithms.twopointers;

public class TrappingRainWater42Hard {

    public int trap(int[] height) {

        if (height == null || height.length == 0) return 0;

        // Initialize two pointers
        int l=0,r=height.length-1;

        // Set left and right two both ends.
        int leftMax = height[l];
        int rightMax = height[r];

        int water = 0;

        while (l < r) {
            // Move whichever pointer has the smaller Max value behind it to the opposite side.
            // So if leftMax is smaller than or equal to rightMax, move left pointer to the right.
            // Otherwise, move right pointer to the left.
            if (leftMax <= rightMax) {
                l++;
                // Update the leftMax to the maximum of the current leftMax and the new height at the left pointer.
                leftMax = Math.max(leftMax, height[l]);
                // Add the difference between the leftMax and the height at the left pointer to the water.
                water += leftMax - height[l];
            } else {
                r--;
                // Update the rightMax to the maximum of the current rightMax and the new height at the right pointer.
                rightMax = Math.max(rightMax, height[r]);
                // Add the difference between the rightMax and the height at the right pointer to the water.
                water += rightMax - height[r];
            }
        }

        return water;
    }

}
