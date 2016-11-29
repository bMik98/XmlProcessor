package ru.magnit.xmlprocessor.property.impl;

import ru.magnit.xmlprocessor.property.ConnectionProperties;

import java.util.Properties;

public class DirectConnectionProperties implements ConnectionProperties {
    @Override
    public String buildUrl() {
        return "jdbc:postgresql://127.0.0.1:5432/xmldb";
    }

    @Override
    public String getDriverName() {
        return "org.postgresql.Driver";
    }

    @Override
    public Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        return props;
    }
}
