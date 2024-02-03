package org.example.algorithms.array;

public class PalindromeNumbers {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int numDigits = numDigits(x);
        if (numDigits == 1) return true;

        while (numDigits>1) {
            int denomToGetLHS = (int) Math.pow(10, (numDigits-1));
            int lhs = x/denomToGetLHS;
            x = x % denomToGetLHS;
            int rhs = x % 10;
            if (lhs != rhs) return false;
            x /= 10;
            numDigits -= 2;
        }
        return true;
    }

    private int numDigits(int num) {
        return (int) Math.floor(Math.log10(num) + 1);
    }
}
