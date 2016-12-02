package ru.magnit.xmlprocessor.service.impl;

import ru.magnit.xmlprocessor.dao.EntryTableDao;
import ru.magnit.xmlprocessor.entity.Entry;
import ru.magnit.xmlprocessor.service.TableService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public class EntryTableService implements TableService {
    private final EntryTableDao dao;

    public EntryTableService(final EntryTableDao dao) {
        this.dao = dao;
    }

    @Override
    public void clearAndPopulateTable(final int numberOfRecords) {
        List<Entry> entries = createEntryList(numberOfRecords);
        dao.deleteAll();
        dao.save(entries);
    }

    @Override
    public List getTableContent() {
        return dao.getAll();
    }

    private List<Entry> createEntryList(final int numberOfRecords) {
        List<Entry> result = new ArrayList<>(numberOfRecords);
        for(int i = 1; i<= numberOfRecords;i++) {
            result.add(new Entry(i));
        }
        return result;
    }
}
