package ru.magnit.xmlprocessor.application.impl;

import ru.magnit.xmlprocessor.application.ProcessingApplication;
import ru.magnit.xmlprocessor.dao.EntriesTableDao;
import ru.magnit.xmlprocessor.dao.TableDao;
import ru.magnit.xmlprocessor.dao.impl.EntriesTableDaoImpl;
import ru.magnit.xmlprocessor.dao.util.ConnectionBuilder;
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
        TableDao dao = new EntriesTableDaoImpl(connection);
        dao.initTable(numberN);
        return 0;
    }

    @Override
    public void setConnectionProperties(final ConnectionProperties properties) {
        this.connectionProperties = properties;
        connection = ConnectionBuilder.build(this.connectionProperties);
    }

    @Override
    public void setNumberN(int numberN) {
        if (numberN < 0) {
            throw new IllegalArgumentException();
        }
        this.numberN = numberN;
    }
}
