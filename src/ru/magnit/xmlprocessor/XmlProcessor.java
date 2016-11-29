package ru.magnit.xmlprocessor;

import ru.magnit.xmlprocessor.application.ProcessingApplication;
import ru.magnit.xmlprocessor.application.impl.XmlDatabaseProcessor;
import ru.magnit.xmlprocessor.property.impl.DirectApplicationProperties;
import ru.magnit.xmlprocessor.property.impl.DirectConnectionProperties;

public class XmlProcessor {

    public static void main(String[] args) {
        new XmlProcessor().doIt(new DirectApplicationProperties().getNumberN());
    }

    public long doIt(final int numberN) {
        ProcessingApplication application = new XmlDatabaseProcessor();
        application.setConnectionProperties(new DirectConnectionProperties());
        application.setNumberN(numberN);
        long result = application.runAndCalculate();
        System.out.println(result);
        return result;
    }
}
