package ru.magnit.xmlprocessor;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {
    @Test
    public void doIt() throws Exception {
        int numberN = 10;
        double expected = (((double) numberN + 1) / 2) * (double) numberN;
        System.out.println(expected);
        long startTime = System.currentTimeMillis();
        long result = new Application().doIt(numberN);
        long timeSpent = System.currentTimeMillis() - startTime;
        assertTrue("Runtime less than 5 min.", timeSpent <= 5 * 60 * 1000);
        assertEquals("Sum of values", (long) expected, result);
    }
}