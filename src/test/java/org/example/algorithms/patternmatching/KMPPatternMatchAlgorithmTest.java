package org.example.algorithms.patternmatching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KMPPatternMatchAlgorithmTest {

    private KMPPatternMatchAlgorithm obj;

    @BeforeEach
    public void setup() {
        obj = new KMPPatternMatchAlgorithm();
    }

    @ParameterizedTest
    @CsvSource({
                ",,true,-1",
                ",'',false,-1",
                "'',,false,-1",
                ",,true,-1",
                "test,'',true,0",
                "'','',true,0",
                "'',testPat,false,-1",
                "testString,testPat,false,-1",
                "testString,ring,true,6",
                "testString,t,true,0"}
    )
    public void testWithNullStrAndPattern(String str, String pat, Boolean assertion, int index) {
        var res = new Result(assertion, index);
        assertEquals(res, obj.search(str, pat));
    }
}
