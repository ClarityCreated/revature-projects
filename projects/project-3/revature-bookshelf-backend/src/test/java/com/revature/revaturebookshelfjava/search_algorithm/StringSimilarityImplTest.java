package com.revature.revaturebookshelfjava.search_algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringSimilarityImplTest {
    private static StringSimilarity stringSimilarity;

    @BeforeEach
    public void init() {
        stringSimilarity = new StringSimilarityImpl();
    }

    @Test
    @DisplayName("Perfect Match Similarity Test")
    public void perfectMatchTest() {
        double actual = stringSimilarity.similarity("one two three four five", "one two three four five");
        Assertions.assertEquals(1,actual);
    }

    @Test
    @DisplayName("Empty Strings Similarity Test")
    public void emptyStringsTest() {
        double actual = stringSimilarity.similarity("","");
        Assertions.assertEquals(1,actual);
    }

    @Test
    @DisplayName("Zero Match Similarity Test")
    public void zeroMatchTest() {
        double actual = stringSimilarity.similarity("","one two three four five");
        Assertions.assertEquals(0,actual);
    }

    @Test
    @DisplayName("Middling Match Similarity Test")
    public void middleMatchTest() {
        double actual = stringSimilarity.similarity("one two three five four","one two three four five");
        Assertions.assertEquals(0.7391304347826086,actual);
    }

    @Test
    @DisplayName("First Input Longer Similarity Test")
    public void s1LongerTest() {
        double actual = stringSimilarity.similarity("longer string","shorter");
        Assertions.assertEquals(0.15384615384615385,actual);
    }

    @Test
    @DisplayName("Second Input Longer Similarity Test")
    public void s2LongerTest() {
        double actual = stringSimilarity.similarity("shorter","longer string");
        Assertions.assertEquals(0.15384615384615385,actual);
    }

    @Test
    @DisplayName("Case Insensitivity Similarity Test")
    public void caseInsensitivityTest() {
        double actual = stringSimilarity.similarity("CASE INSENSITIVE","case insensitive");
        Assertions.assertEquals(1,actual);
    }
}
