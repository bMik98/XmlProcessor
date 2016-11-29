package ru.magnit.xmlprocessor.dao;

import java.util.List;

public interface TableDao<T> {
    void initTable(int numberOfEntities);

    List<T> getAll();
}
