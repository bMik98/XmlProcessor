package ru.magnit.xmlprocessor.property.impl;

import ru.magnit.xmlprocessor.property.ConnectionProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileConnectionProperties implements ConnectionProperties {
    private Properties properties;
    private String url;
    private String driverName;

    public FileConnectionProperties(final String fileName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            prop.load(input);
            url = prop.getProperty("url");
            prop.remove("url");
            driverName = prop.getProperty("driverName");
            prop.remove("driverName");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        properties = prop;
    }

    @Override
    public String buildUrl() {
        return url;
    }

    @Override
    public String getDriverName() {
        return driverName;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }
}
