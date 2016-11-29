package ru.magnit.xmlprocessor.application.impl;

import ru.magnit.xmlprocessor.application.ProcessingApplication;
import ru.magnit.xmlprocessor.dao.ConnectionBuilder;
import ru.magnit.xmlprocessor.property.ConnectionProperties;

import java.sql.Connection;

public class XmlDatabaseProcessor implements ProcessingApplication {
    private ConnectionProperties connectionProperties;
    private int numberN;
    private Connection connection;

    public XmlDatabaseProcessor() {
        this.numberN = 0;
    }

    @Override
    public long runAndCalculate() {
        return 0;
    }

    public void setConnectionProperties(final ConnectionProperties properties) {
        this.connectionProperties = properties;
        connection = ConnectionBuilder.build(this.connectionProperties);
    }

    public void setNumberN(int numberN) {
        if (numberN < 0) {
            throw new IllegalArgumentException();
        }
        this.numberN = numberN;
    }
}
