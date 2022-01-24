package com.revature.revaturebookshelfjava.search_algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringContainmentImplTest {
    private static StringContainment stringContainment;

    @BeforeEach
    public void init() {
        stringContainment = new StringContainmentImpl();
    }

    @Test
    @DisplayName("Perfect Match Containment Test")
    public void perfectMatchTest() {
        int actual = stringContainment.contains("one two three four five","one two three four five");
        Assertions.assertEquals(5,actual);
    }

    @Test
    @DisplayName("Zero Match Containment Test")
    public void zeroMatchTest() {
        int actual = stringContainment.contains("one two three four five", "six seven eight nine ten");
        Assertions.assertEquals(0,actual);
    }

    @Test
    @DisplayName("No Blacklist Containment Test")
    public void shortInputBlacklistTest() {
        int actual = stringContainment.contains("the and","the dog");
        Assertions.assertEquals(1,actual);
    }

    @Test
    @DisplayName("Blacklist Containment Test")
    public void longInputBlacklistTest() {
        int actual = stringContainment.contains("a an and the by for of with to but at in on so","pretty fly for a white guy");
        Assertions.assertEquals(0,actual);
    }

    @Test
    @DisplayName("Case Insensitivity Containment Test")
    public void caseInsensitivityTest() {
        int actual = stringContainment.contains("CASE INSENSITIVE","case insensitive");
        Assertions.assertEquals(2,actual);
    }
}
