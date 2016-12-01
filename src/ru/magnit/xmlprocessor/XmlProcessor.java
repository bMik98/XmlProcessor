package ru.magnit.xmlprocessor;

import ru.magnit.xmlprocessor.dao.EntryTableDao;
import ru.magnit.xmlprocessor.dao.impl.EntryTableDaoImpl;
import ru.magnit.xmlprocessor.dao.util.ConnectionBuilder;
import ru.magnit.xmlprocessor.entity.Entry;
import ru.magnit.xmlprocessor.property.ConnectionProperties;
import ru.magnit.xmlprocessor.xml.EntryXmlJuggler;
import ru.magnit.xmlprocessor.xml.impl.EntryXmlJugglerImpl;

import java.io.File;
import java.sql.Connection;
import java.util.List;

public class XmlProcessor implements ProcessingApplication {
    private final String file1 = "resources/1.xml";
    private final String file2 = "resources/2.xml";
    private final String xslFile = "resources/transform.xsl";
    private ConnectionProperties connectionProperties;
    private int numberN;
    private EntryTableDao dao;
    private EntryXmlJuggler xmlJuggler = new EntryXmlJugglerImpl();

    @Override
    public long run() {
        initAndPopulateDbTable(numberN);
        saveToXmlFile(dao.getAll(), file1);
        xslTransform(file1, xslFile, file2);
        List<Entry> entries = LoadFromXmlFile(file2);
        return CalcSumOfFields(entries);
    }

    private List<Entry> LoadFromXmlFile(final String fileName) {
        return xmlJuggler.loadFromFile(new File(fileName));
    }

    private long CalcSumOfFields(List<Entry> entries) {
        long result = 0L;
        for (Entry entry : entries) {
            result += entry.getField();
        }
        return result;
    }

    private void initAndPopulateDbTable(final int numberOfElements) {
        dao.initTable();
        dao.populateTable(numberOfElements);
    }

    private void saveToXmlFile(final List<Entry> entries, final String fileName) {
        xmlJuggler.saveToFile(entries, new File(fileName));
    }

    private void xslTransform(final String in, final String xslt, final String out) {
        xmlJuggler.transform(in, xslt, out);
    }

    @Override
    public void setConnectionProperties(final ConnectionProperties properties) {
        this.connectionProperties = properties;
        Connection connection = ConnectionBuilder.build(this.connectionProperties);
        this.dao = new EntryTableDaoImpl(connection);
    }

    @Override
    public void setNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        this.numberN = number;
    }
}
