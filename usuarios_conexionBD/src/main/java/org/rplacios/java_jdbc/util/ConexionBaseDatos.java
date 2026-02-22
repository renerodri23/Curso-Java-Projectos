package org.rplacios.java_jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static String url ="jdbc:mysql://localhost:3306/esquema_usuario?serverTimezone=UTC";
    private static String user = "root";
    private static String password = "root";
    private static Connection conn;

    public static Connection getInstance() throws SQLException {
        if (conn == null){
            conn = DriverManager.getConnection(url,user,password);
        }
        return conn;
    }
}
