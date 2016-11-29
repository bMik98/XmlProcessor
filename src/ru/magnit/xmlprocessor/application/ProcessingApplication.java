package ru.magnit.xmlprocessor.application;

import ru.magnit.xmlprocessor.property.ConnectionProperties;

public interface ProcessingApplication {
    long runAndCalculate();

    void setConnectionProperties(ConnectionProperties properties);

    void setNumberN(int numberN);
}
