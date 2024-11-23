package org.example.algorithms.calendar;

import java.util.*;

public class Solution{
    public static boolean validWordAbbreviation(String word, String abbr) {
        int j = 0;
        for (int i=0;i<abbr.length();i++) {
            char ch = abbr.charAt(i);
            if (Character.isDigit(ch)) {
                int num = (int) (ch) - ('0');
                while (Character.isDigit(abbr.charAt(i+1))) {
                    ch = abbr.charAt(i+1);
                    num *= 10;
                    num += (int) (ch) - ('0');
                    i++;
                }
                if (j + num >= word.length()) return false; // if num exceeds the rem length of the word.
                j += num; // skip that many characters.
            } else if (ch != word.charAt(j++)) {
                return false;
            }
        }
        // Replace the following return statement with your code
        return true;
    }

    public static void main(String[] args) {
        System.out.println(validWordAbbreviation("internationalization", "13iz4n"));
    }
}