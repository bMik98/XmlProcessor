package ru.magnit.xmlprocessor;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void doIt() throws Exception {
        int numberN = 1000000;
        double expected = ((1.0 + numberN) / 2) * numberN;
        long startTime = System.currentTimeMillis();
        long result = new Main().doIt(numberN);
        long timeSpent = System.currentTimeMillis() - startTime;
        assertTrue("Runtime less than 5 min.", timeSpent <= 5 * 60 * 1000);
        assertEquals("Sum of values", (long) expected, result);
    }
}