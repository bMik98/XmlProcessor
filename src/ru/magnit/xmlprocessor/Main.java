package ru.magnit.xmlprocessor;

import ru.magnit.xmlprocessor.property.impl.FileConnectionProperties;

public class Main {
    private final static int NUMBER = 10;

    public static void main(String[] args) {
        new Main().doIt((args.length == 1)? Integer.parseInt(args[0]) : NUMBER);
    }

    public long doIt(final int numberN) {
        ProcessingApplication application = new XmlProcessor();
        application.setConnectionProperties(
                new FileConnectionProperties("resources/dbconnection.properties"));
        application.setNumber(numberN);
        long result = application.run();
        System.out.println(result);
        return result;
    }
}
