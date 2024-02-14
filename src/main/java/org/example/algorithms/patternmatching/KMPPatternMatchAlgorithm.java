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
        var notFound = Result.NOT_FOUND;
        if (pat == null) {
            return new Result(str == null, -1);
        }
        if (str == null) return notFound;
        if (pat.length() == 0) return new Result(true, 0);
        if (str.length() == 0) return notFound;

        int[] kmpArr = getKMPArray(pat);
        int strPtr=0;
        int patPtr=0;
        while ((strPtr<str.length()) && (patPtr<pat.length())) {
            if (str.charAt(strPtr) == pat.charAt(patPtr)) {
                strPtr++;
                patPtr++;
                if (patPtr == pat.length()) {
                    return new Result(true, strPtr-patPtr);
                }
            }
            else {
                if (patPtr==0) {
                    strPtr++;
                }
                else {
                    patPtr = kmpArr[patPtr-1];
                }
            }
        }
        return notFound;
    }
}

class Result {
    boolean exists;
    int index;

    static Result NOT_FOUND = new Result(false, -1);

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
