package main_func;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Bill_fun{
    private static String query;
    static Connection conn = null;
    public static void con_database() throws SQLException{
        String url = "jdbc:sqlserver://localhost:1433;databaseName=photo_shop;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true;";
        conn =DriverManager.getConnection(url);
    }
    public static int get_numb() throws SQLException{
        con_database();
        Statement st = null;
        ResultSet rs = null;
        int id=0;
            query = "Select numb from number";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                return id = rs.getInt(1);
            }
            rs.close();
            st.close();
            return id;
        }
    public static void up_numb() throws SQLException{
        con_database();
        Statement st =null;
        query = "Update number set numb = numb+1";
        st =conn.createStatement();
        st.executeUpdate(query);
        st.close();
    }
    public static int get_bnumb() throws SQLException{
        con_database();
        Statement st = null;
        ResultSet rs = null;
        int id=0;
            query = "Select b_numb from number";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                return id = rs.getInt(1);
            }
            rs.close();
            st.close();
            return id;
        }
    public static void set_bnumb() throws SQLException{
        con_database();
        Statement st = null;
        query = "Update number set b_numb =1";
        st =conn.createStatement();
        st.executeUpdate(query);
        st.close();
    }
    public static void up_bnumb() throws SQLException{
        con_database();
        Statement st =null;
        query = "Update number set b_numb = b_numb+1";
        st =conn.createStatement();
        st.executeUpdate(query);
        st.close();
    }
    public static void insert_cust(String cust_name) throws SQLException{
        con_database();
        String cust_id="";
        int id = get_numb();
            if(id<10){
                cust_id = "C000" +String.valueOf(id);
            }
            if (id>=10 && id<=99){
                cust_id = "C00" +String.valueOf(id);
            }
            if (id>99&&id<=999){
                cust_id = "C0" +String.valueOf(id);
            }
            if (id>999&&id<=9999){
                cust_id = "C" +String.valueOf(id);
            }
            query = "insert into customer(cust_id, cust_name) values (?,?)";
            PreparedStatement pr = null;
            pr = conn.prepareStatement(query);
            pr.setString(1, cust_id);
            pr.setString(2, cust_name);
            pr.executeUpdate();
            pr.close();
        }
    public static void insert_bill(String orders, int quantity) throws SQLException {
        con_database();
        int id = get_numb();
        int b_id = get_bnumb();
        int unit_price=0;
        String cust_id="", bill_id ="";
        PreparedStatement pr =null;
        query = "Insert into bills(cust_id, bill_id, cust_order, unit_price, quantity) values (?,?,?,?,?)";
        if (orders.equals("Black and white A3")){
            unit_price = 1000;
        }
        if (orders.equals("Black and white A4")){
            unit_price = 500;
        }
        if (orders.equals("Black and white A5")){
            unit_price = 300;
        }
        if (orders.equals("Color A3")){
            unit_price = 5000;
        }
        if (orders.equals("Color A4")){
            unit_price = 3000;
        }
        if (orders.equals("Color A5")){
            unit_price = 2000;
        }
        if(id<10){
            cust_id = "C000" +String.valueOf(id);
        }
        if (id>=10 && id<=99){
            cust_id = "C00" +String.valueOf(id);
        }
        if (id>99&&id<=999){
            cust_id = "C0" +String.valueOf(id);
        }
        if (id>999&&id<=9999){
            cust_id = "C" +String.valueOf(id);
        }
        if (b_id<10){
            bill_id = "B0" + String.valueOf(b_id);
        }
        if (b_id>=10){
            bill_id = "B" + String.valueOf(b_id);
        }
        pr = conn.prepareStatement(query);
        pr.setString(1, cust_id);
        pr.setString(2, bill_id);
        pr.setString(3, orders);
        pr.setInt(4, unit_price);
        pr.setInt(5, quantity);
        pr.executeUpdate();
        pr.close();
    }
    public static String get_cust_id() throws SQLException{
        con_database();
        int id = get_numb();
        String cust_id="";
        if(id<10){
            cust_id = "C000" +String.valueOf(id);
        }
        if (id>=10 && id<=99){
            cust_id = "C00" +String.valueOf(id);
        }
        if (id>99&&id<=999){
            cust_id = "C0" +String.valueOf(id);
        }
        if (id>999&&id<=9999){
            cust_id = "C" +String.valueOf(id);
        }
        query = "Select cust_id from customer where cust_id = ?";
        PreparedStatement pr =null;
        ResultSet rs = null;
        pr = conn.prepareStatement(query);
        pr.setString(1, cust_id);
        rs = pr.executeQuery();
        if(rs.next()){
            return rs.getString("cust_id");
        }
        else {
            return null;
        }
    }
    public static int get_unit_price(String cust_id, String cust_order) throws SQLException{
        con_database();
        query = "Select unit_price from bills where cust_id = ? and cust_order =?";
        PreparedStatement pr = null;
        ResultSet rs = null;
        pr = conn.prepareStatement(query);
        pr.setString(1, cust_id);
        pr.setString(2, cust_order);
        rs = pr.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        else{
        return 0;
        }
    }
    public static long get_price(String cust_id, String cust_order) throws SQLException{
        con_database();
        int id= get_bnumb();
        String bill_id = "";
        if (id<10){
            bill_id = "B0" + String.valueOf(id);
        }
        if (id>=10){
            bill_id = "B" + String.valueOf(id);
        }
        query = "Select price from bills where cust_id = ? and cust_order = ? and bill_id = ?";
        PreparedStatement pr = null;
        ResultSet rs = null;
        pr = conn.prepareStatement(query);
        pr.setString(1, cust_id);
        pr.setString(2, cust_order);
        pr.setString(3, bill_id);
        rs = pr.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        else{
        return 0;
        }
    }
    public static long get_total_price(String cust_id) throws SQLException {
        con_database();
        query = "select Sum(price) from bills where cust_id = ?";
        PreparedStatement pr = null;
        ResultSet rs = null;
        pr = conn.prepareStatement(query);
        pr.setString(1, cust_id);
        rs = pr.executeQuery();
        if (rs.next()){
            return rs.getLong(1);
        }
        return 0;
    }
    public static boolean search_bill(String cust_id) throws SQLException{
        con_database();
        query = "Select cust_id from customer where cust_id = ?";
        PreparedStatement pr = null;
        ResultSet rs = null;
        pr = conn.prepareStatement(query);
        pr.setString(1, cust_id);
        rs = pr.executeQuery();
        if (rs.next()){
            return true;
        }
        else{
            return false;
        }
    }
    public static void delete_bill(String cust_id) throws SQLException{
        con_database();
        query = "Delete from customer where cust_id = ?";
        PreparedStatement pr = null;
        pr = conn.prepareStatement(query);
        pr.setString(1, cust_id);
        pr.executeUpdate();
        pr.close();
    }
}
