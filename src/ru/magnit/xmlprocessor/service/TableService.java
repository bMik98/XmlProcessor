package ru.magnit.xmlprocessor.service;

import java.util.List;

/**
 * @author Mbedritskiy
 */
public interface TableService<T> {
    void clearAndPopulateTable(int numberOfRecords);

    List<T> getTableContent();
}
