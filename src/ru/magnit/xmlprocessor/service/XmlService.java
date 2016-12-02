package ru.magnit.xmlprocessor.service;

import java.io.File;
import java.util.List;

public interface XmlService<T> {
    void saveToFile(List<T> entities, File file);

    List<T> loadFromFile(File file);

    void transform(String dataXml, String inputXsl, String outputXml);
}
