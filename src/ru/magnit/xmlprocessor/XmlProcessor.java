package ru.magnit.xmlprocessor;

import ru.magnit.xmlprocessor.dao.EntryTableDao;
import ru.magnit.xmlprocessor.dao.impl.EntryTableDaoImpl;
import ru.magnit.xmlprocessor.dao.util.ConnectionBuilder;
import ru.magnit.xmlprocessor.entity.Entry;
import ru.magnit.xmlprocessor.property.ConnectionProperties;
import ru.magnit.xmlprocessor.service.impl.EntryTableService;
import ru.magnit.xmlprocessor.service.EntryXmlService;
import ru.magnit.xmlprocessor.service.impl.EntryXmlServiceImpl;

import java.io.File;
import java.sql.Connection;
import java.util.List;

public class XmlProcessor implements ProcessingApplication {
    private final String file1 = "resources/1.xml";
    private final String file2 = "resources/2.xml";
    private final String xslFile = "resources/transform.xsl";
    private ConnectionProperties connectionProperties;
    private int numberN;

    @Override
    public long run() {
        Connection connection = ConnectionBuilder.build(this.connectionProperties);
        EntryTableDao dao = new EntryTableDaoImpl(connection);
        EntryTableService tableService = new EntryTableService(dao);
        tableService.clearAndPopulateTable(numberN);
        EntryXmlService xmlService = new EntryXmlServiceImpl();
        xmlService.saveToFile(tableService.getTableContent(), new File(file1));
        xmlService.transform(file1, xslFile, file2);
        List<Entry> entries = xmlService.loadFromFile(new File(file2));
        return sumOfField(entries);
    }

    private long sumOfField(List<Entry> entries) {
        long result = 0L;
        for (Entry entry : entries) {
            result += entry.getField();
        }
        return result;
    }

    @Override
    public void setConnectionProperties(final ConnectionProperties properties) {
        this.connectionProperties = properties;
    }

    @Override
    public void setNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        this.numberN = number;
    }
}
