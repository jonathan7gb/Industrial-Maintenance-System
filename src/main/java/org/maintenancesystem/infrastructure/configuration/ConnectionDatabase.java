package org.maintenancesystem.infrastructure.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {

    private static final String URL = "jdbc:mysql://127.0.0.1:3357/maintenance_system?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "mysqlPW";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
