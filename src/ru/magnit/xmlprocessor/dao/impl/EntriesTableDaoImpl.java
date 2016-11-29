package ru.magnit.xmlprocessor.dao.impl;

import ru.magnit.xmlprocessor.dao.EntriesTableDao;
import ru.magnit.xmlprocessor.entity.Entry;

import java.sql.Connection;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public class EntriesTableDaoImpl implements EntriesTableDao {
    private final String TABLE_NAME = "TEST";
    private final String FIELD_NAME = "FIELD";
    private Connection connection;

    public EntriesTableDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void initTable(int numberOfEntities) {
        
    }

    @Override
    public List<Entry> getAll() {
        return null;
    }
}
