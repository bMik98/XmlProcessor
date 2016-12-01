package ru.magnit.xmlprocessor.dao.impl;

import ru.magnit.xmlprocessor.dao.EntryTableDao;
import ru.magnit.xmlprocessor.entity.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public class EntryTableDaoImpl implements EntryTableDao {
    private final String TABLE_NAME = "TEST";
    private final String FIELD_NAME = "FIELD";
    private Connection connection;

    public EntryTableDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void initTable() {
        final String dropSql = String.format("DROP TABLE IF EXISTS \"%s\"", TABLE_NAME);
        final String createSql = String.format("CREATE TABLE \"%s\" (\"%s\" INTEGER)", TABLE_NAME, FIELD_NAME);
        try {
            Statement statement = connection.createStatement();
            statement.execute(dropSql);
            statement.execute(createSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void populateTable(final int numberOfEntities) {
        final String insertSql = String.format("INSERT INTO \"%s\" (\"%s\") VALUES (?)", TABLE_NAME, FIELD_NAME);
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(insertSql);
            for (int i = 1; i < numberOfEntities + 1; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Entry> getAll() {
        List<Entry> result = new ArrayList<>();
        ResultSet queryResult = queryAll();
        int fieldIndex = getFieldIndex(queryResult, FIELD_NAME);
        try {
            while (queryResult.next()) {
                Entry entry = new Entry();
                entry.setField(queryResult.getInt(fieldIndex));
                result.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int getFieldIndex(final ResultSet resultSet, final String fieldName) {
        int index = 0;
        try {
            index = resultSet.findColumn(fieldName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }

    private ResultSet queryAll() {
        final String selectSql = String.format("SELECT * FROM \"%s\"", TABLE_NAME);
        ResultSet result = null;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(selectSql);
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
