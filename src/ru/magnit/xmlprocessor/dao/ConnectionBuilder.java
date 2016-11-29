package ru.magnit.xmlprocessor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionBuilder {
    public static Connection getConnection() {
        initJdbcDriver();
        Connection connection = null;
        String url = "jdbc:postgresql://127.0.0.1:5432/xmldb";
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }

    private static boolean initJdbcDriver() {
        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return false;
        }

        System.out.println("PostgreSQL JDBC Driver Registered!");
        return true;
    }
}
