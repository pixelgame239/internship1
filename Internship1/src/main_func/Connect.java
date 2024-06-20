package main_func;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    static Connection conn = null;
    public static void con_database() throws SQLException{
        String url = "jdbc:sqlserver://localhost:1433;databaseName=photo_shop;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true;";
        conn =DriverManager.getConnection(url);
    }
    public static Connection getConnection(){
        return conn;
    }
}
    