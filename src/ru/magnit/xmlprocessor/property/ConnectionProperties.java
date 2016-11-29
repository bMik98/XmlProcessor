package ru.magnit.xmlprocessor.property;

import java.util.Properties;

public interface ConnectionProperties {
    String buildUrl();

    String getDriverName();

    Properties getProperties();
}
