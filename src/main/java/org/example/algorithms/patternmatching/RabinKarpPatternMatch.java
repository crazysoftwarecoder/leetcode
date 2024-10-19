package org.example.algorithms.patternmatching;

public class RabinKarpPatternMatch {

    private static final int PRIME = 101;
    private static final long MOD = (long) 1e9 + 7;

    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        if (haystack.length() < needle.length()) return -1;

        long needleHash = hash(needle);
        long hayHash = 0;
        long h = 1; // This will be PRIME^(m-1) % MOD

        // Precompute PRIME^(m-1) % MOD
        for (int i = 0; i < needle.length() - 1; i++) {
            h = (h * PRIME) % MOD;
        }

        for (int i = 0; i < haystack.length(); i++) {
            // Add the next character to the hash
            hayHash = (hayHash * PRIME + haystack.charAt(i)) % MOD;

            // If our window is the right size, compare hashes
            if (i >= needle.length() - 1) {
                if (hayHash == needleHash) {
                    // Check the actual substring to avoid false positives due to hash collision
                    if (haystack.substring(i - needle.length() + 1, i + 1).equals(needle)) {
                        return i - needle.length() + 1;
                    }
                }
                // Remove the old character from the hash
                hayHash = (hayHash - haystack.charAt(i - needle.length() + 1) * h % MOD + MOD) % MOD;
            }
        }
        return -1;
    }

    private long hash(String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash * PRIME + s.charAt(i)) % MOD;
        }
        return hash;
    }

    public static void main(String[] args) {
        var rabinKarpPattern = new RabinKarpPatternMatch();
        System.out.println(rabinKarpPattern.strStr("abbbbbaabbaabaabbbaaaaabbabbbabbbbbaababaabbaabbbbbababaababbbbaaabbbbabaabaaaabbbbabbbaabbbaabbaaabaabaaaaaaaa", "abbbaababbbabbbabbbbbabaaaaaaabaabaabbbbaabab"));
    }
}