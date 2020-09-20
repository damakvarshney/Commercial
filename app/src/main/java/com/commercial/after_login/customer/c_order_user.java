package com.commercial.after_login.customer;


import java.util.List;

public class c_order_user {
    List<c_order_item_user> orders;
    int total_of_amt =0;
    int t_quantity = 0;
    String order_status="Not Delivered";
    String pay_status = "Not Paid";


    String date;
    long order_id;
    String customer_name;
    String payment_mode;
    long payment_id;
    String sign="Signature Url ";

    public c_order_user(List<c_order_item_user> orders, int total_of_amt, int t_quantity, String order_status, String pay_status, String date, long order_id, String customer_name, String payment_mode, long payment_id,String sign) {
        this.orders = orders;
        this.total_of_amt = total_of_amt;
        this.t_quantity = t_quantity;
        this.order_status = order_status;
        this.pay_status = pay_status;
        this.date = date;
        this.order_id = order_id;
        this.customer_name = customer_name;
        this.payment_mode = payment_mode;
        this.payment_id = payment_id;
        this.sign=sign;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public c_order_user() {
    }

    public List<c_order_item_user> getOrders() {
        return orders;
    }

    public void setOrders(List<c_order_item_user> orders) {
        this.orders = orders;
    }

    public int getTotal_of_amt() {
        return total_of_amt;
    }

    public void setTotal_of_amt(int total_of_amt) {
        this.total_of_amt = total_of_amt;
    }

    public int getT_quantity() {
        return t_quantity;
    }

    public void setT_quantity(int t_quantity) {
        this.t_quantity = t_quantity;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(long payment_id) {
        this.payment_id = payment_id;
    }
}
