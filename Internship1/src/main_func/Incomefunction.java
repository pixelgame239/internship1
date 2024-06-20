package main_func;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Incomefunction {
    private static String query;
    static Connection conn = null;
    public static void con_database() throws SQLException{
        String url = "jdbc:sqlserver://localhost:1433;databaseName=photo_shop;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true;";
        conn =DriverManager.getConnection(url);
    }
    public static void insert_income(String cust_id, long total_price) throws SQLException{
        con_database();
        query = "Insert into income(cust_id, total_price) values (?,?)";
        PreparedStatement pr = null;
        pr = conn.prepareStatement(query);
        pr.setString(1, cust_id);
        pr.setLong(2, total_price);
        pr.executeUpdate();
        pr.close();
    }
    public static long tt_income() throws SQLException{
        con_database();
        query = "Select Sum(total_price) from income";
        Statement st = null;
        ResultSet rs = null;
        st = conn.createStatement();
        rs = st.executeQuery(query);
        while (rs.next()){
            return rs.getLong(1);
        }
        return 0;
    }
}
