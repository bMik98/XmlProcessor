package ru.magnit.xmlprocessor.dao;

import java.util.List;

public interface TableDao<T> {
    void initTable();

    void populateTable(int numberOfEntities);

    List<T> getAll();
}
