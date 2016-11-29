package ru.magnit.xmlprocessor.application.impl;

import ru.magnit.xmlprocessor.application.ProcessingApplication;
import ru.magnit.xmlprocessor.property.ConnectionProperties;

import java.sql.Connection;

public class XmlDatabaseProcessor implements ProcessingApplication {
    private ConnectionProperties connectionProperties;
    private int numberN;

    public XmlDatabaseProcessor() {
        this.numberN = 0;
    }

    @Override
    public long runAndCalculate() {
        return 0;
    }

    public void setConnectionProperties(final ConnectionProperties properties) {
        this.connectionProperties = properties;
    }

    public void setNumberN(int numberN) {
        this.numberN = numberN;
    }
}
