package ru.magnit.xmlprocessor.dao;

import java.util.List;

public interface TableDao<T> {
    void deleteAll();

    void save(List<T> entities);

    List<T> getAll();

    int count();
}
