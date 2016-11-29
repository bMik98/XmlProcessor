package ru.magnit.xmlprocessor.dao;

import ru.magnit.xmlprocessor.property.ConnectionProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionBuilder {
    public static Connection build(final ConnectionProperties properties) {
        initJdbcDriver(properties.getDriverName());
        Connection connection;
        try {
            connection = DriverManager.getConnection(properties.buildUrl(), properties.getProperties());
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        if (connection == null) {
            System.err.println("Failed to make connection!");
        }
        return connection;
    }

    private static boolean initJdbcDriver(final String driverName) {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return false;
        }

        System.out.println("PostgreSQL JDBC Driver Registered!");
        return true;
    }
}
