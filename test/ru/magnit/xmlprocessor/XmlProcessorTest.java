package ru.magnit.xmlprocessor;

import org.junit.Test;

import static org.junit.Assert.*;

public class XmlProcessorTest {
    @Test
    public void doIt() throws Exception {
        int numberN = 10;
        double expected = ((1.0 + numberN) / 2) * numberN;
        long startTime = System.currentTimeMillis();
        long result = new XmlProcessor().doIt(numberN);
        long timeSpent = System.currentTimeMillis() - startTime;
        assertTrue("Runtime less than 5 min.", timeSpent <= 5 * 60 * 1000);
        assertEquals("Sum of values", (long) expected, result);
    }
}