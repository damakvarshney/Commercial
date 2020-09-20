package com.commercial.after_login.retailer;

import java.util.List;

public class r_order_user {

    List<r_order_item_user> orders;
    int total_of_amt =0;
    int t_quantity = 0;
    int dis_percent;
    int grandtotal;
    String order_status = "Not delivered";
    String pay_status = "Not Paid";

    String date;

    int discount_amount;
    long order_id;
    String retailer_name;
    String payment_mode;
    long payment_id;
    String sign="";

    public r_order_user(List<r_order_item_user> orders, int total_of_amt, int t_quantity, int dis_percent, int grandtotal, String order_status, String pay_status, String date, int discount_amount, long order_id, String retailer_name, String payment_mode, long payment_id,String sign) {
        this.orders = orders;
        this.total_of_amt = total_of_amt;
        this.t_quantity = t_quantity;
        this.dis_percent = dis_percent;
        this.grandtotal = grandtotal;
        this.order_status = order_status;
        this.pay_status = pay_status;
        this.date = date;
        this.discount_amount = discount_amount;
        this.order_id = order_id;
        this.retailer_name = retailer_name;
        this.payment_mode = payment_mode;
        this.payment_id = payment_id;
        this.sign=sign;
    }

    public r_order_user() {
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<r_order_item_user> getOrders() {
        return orders;
    }

    public void setOrders(List<r_order_item_user> orders) {
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

    public int getDis_percent() {
        return dis_percent;
    }

    public void setDis_percent(int dis_percent) {
        this.dis_percent = dis_percent;
    }

    public int getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(int grandtotal) {
        this.grandtotal = grandtotal;
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

    public int getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(int discount_amount) {
        this.discount_amount = discount_amount;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getRetailer_name() {
        return retailer_name;
    }

    public void setRetailer_name(String retailer_name) {
        this.retailer_name = retailer_name;
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
