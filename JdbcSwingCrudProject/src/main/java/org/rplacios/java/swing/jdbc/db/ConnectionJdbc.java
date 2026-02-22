package org.rplacios.java.swing.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJdbc {

    private static String ucl = "jdbc:mysql://localhost:3306/swing_rows?serverTimezone=America/El_Salvador";
    private static String user = "root";
    private static String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ucl,user,password);
    }
}
