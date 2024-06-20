package main_func;


public class Bill_inf {
    private String cust_id;
    private String cust_orders;
    private int quantity;
    private int u_price;
    private long price;

    public Bill_inf(String cust_id, String cust_orders, int quantity, int u_price, long price){
        this.cust_id = cust_id;
        this.cust_orders = cust_orders;
        this.quantity = quantity;
        this.u_price = u_price;
        this.price = price;
    }
    public String getCust_id(){
        return cust_id;
    }
    public String getCust_orders(){
        return cust_orders;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getU_price(){
        return u_price;
    }
    public long getPrice(){
        return price;
    }
}
