package main_func;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;

public class Openbill {
    public static void open_bill(String cust_id){
        File openbill = new File("E:\\Homework\\Codefolder\\Java\\Internship1\\bill\\bill_" +cust_id + ".txt");
        try{
            Desktop desk = Desktop.getDesktop();
            desk.open(openbill);
        }
        catch (IOException e){
            e.getMessage();
        }
    }
    public static void delete_file(String cust_id){
        File d_bill = new File("E:\\Homework\\Codefolder\\Java\\Internship1\\bill\\bill_" +cust_id + ".txt");
        d_bill.delete();
    }
}
