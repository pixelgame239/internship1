package main_func;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writebillfunction {
    public static void writename(String cust_id, String cust_name) throws IOException{
        File e_bill = new File("E:\\Homework\\Codefolder\\Java\\Internship1\\bill\\bill_" + cust_id + ".txt");
        e_bill.createNewFile();
        FileWriter f_write = new FileWriter("E:\\Homework\\Codefolder\\Java\\Internship1\\bill\\bill_" + cust_id + ".txt");
        f_write.write("BILL\nCustomer's ID: "+ cust_id +"\nCustomer name: " + cust_name);
        f_write.close();
    }
    public static void writeorder(String cust_id, String cust_order, String quantity, String unit_price, String price){
        try (BufferedWriter f_write = new BufferedWriter(new FileWriter("E:\\Homework\\Codefolder\\Java\\Internship1\\bill\\bill_" + cust_id + ".txt",true))){
            f_write.newLine();
            f_write.write("Orders: " + cust_order + " -Quantity: " + quantity + " -Unit price: " + unit_price + " -Price: " + price);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static  void writettprice(String cust_id, String total_price) {
        try (BufferedWriter f_write = new BufferedWriter(new FileWriter("E:\\Homework\\Codefolder\\Java\\Internship1\\bill\\bill_" + cust_id + ".txt", true))){
            f_write.newLine();
            f_write.write("Total price: " + total_price);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
