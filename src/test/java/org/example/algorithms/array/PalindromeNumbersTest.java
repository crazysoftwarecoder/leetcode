package org.example.algorithms.array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeNumbersTest {

    private PalindromeNumbers obj;

    @BeforeEach
    public void setup() {
        obj = new PalindromeNumbers();
    }

    @Test
    public void negativeNumPalindromeTest() {
        assertFalse(obj.isPalindrome(-121));
    }

    @Test
    public void singleDigitTest() {
        assertTrue(obj.isPalindrome(9));
    }

    @Test
    public void twoDigitFalseTest() {
        assertFalse(obj.isPalindrome(23));
    }

    @Test
    public void twoDigitTrueTest() {
        assertTrue(obj.isPalindrome(99));
    }

    @Test
    public void multipleDigitFalseTest() {
        assertFalse(obj.isPalindrome(1234567890));
    }

    @Test
    public void multipleDigitTrueTest() {
        assertTrue(obj.isPalindrome(1234554321));
    }

    @Test
    public void integerMaxMinTest() {
        assertFalse(obj.isPalindrome(Integer.MAX_VALUE));
        assertFalse(obj.isPalindrome(Integer.MIN_VALUE));
    }
}
