package ru.magnit.xmlprocessor;

import ru.magnit.xmlprocessor.property.ConnectionProperties;

public interface ProcessingApplication {
    long run();

    void setConnectionProperties(ConnectionProperties properties);

    void setNumber(int number);
}
