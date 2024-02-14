package org.example.algorithms.patternmatching;

import java.util.Objects;

public class KMPPatternMatchAlgorithm {

    private int[] getKMPArray(String pattern) {
        char[] ch = pattern.toCharArray();
        int[] chIntArr = new int[ch.length];
        int i=0;
        int j=1;
        while (j < pattern.length()) {
            if (ch[i] == ch[j]) {
                chIntArr[j] = chIntArr[i] + 1;
                i++;
                j++;
            }
            else {
                if (i == 0) {
                    chIntArr[j] = 0;
                    j++;
                }
                else {
                    i = chIntArr[i - 1];
                }
            }
        }
        return chIntArr;
    }

    public Result search(String str, String pat) {
        var notFound = new Result(false, -1);
        if (pat == null) {
            return new Result(str == null, -1);
        }
        if (str == null) return notFound;
        if (pat.length() == 0) return new Result(true, 0);
        if (str.length() == 0) return notFound;

        int[] kmpArr = getKMPArray(pat);
        int i=0;
        int j=0;
        while ((i<str.length()) && (j<pat.length())) {
            if (str.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
                if (j == pat.length()) {
                    return new Result(true, i-j);
                }
            }
            else {
                if (j==0) {
                    i++;
                }
                else {
                    j = kmpArr[j-1];
                }
            }
        }
        return notFound;
    }
}

class Result {
    boolean exists;
    int index;

    public Result(boolean exists, int index) {
        this.exists = exists;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return exists == result.exists && index == result.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exists, index);
    }
}
