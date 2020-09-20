package com.commercial.after_login.vendor;

import java.util.List;

public class v_order_user {
    List<v_order_item_user> orders;
    int total_of_amt = 0;
    int t_quantity = 0;
    int dis_percent;
    int grandtotal;
    String date;
    String order_status = "Not delivered";
    String pay_status = "Not Paid";
    int discount_amount;
    long order_id;
    String vendor_name;
    String payment_mode;
    long payment_id;
    String sign = "Signature Url ";

    public v_order_user(List<v_order_item_user> orders, int total_of_amt, int t_quantity, int dis_percent, int grandtotal, String date, String order_status, String pay_status, int discount_amount, long order_id, String vendor_name, String payment_mode, long payment_id, String sign) {
        this.orders = orders;
        this.total_of_amt = total_of_amt;
        this.t_quantity = t_quantity;
        this.dis_percent = dis_percent;
        this.grandtotal = grandtotal;
        this.date = date;
        this.order_status = order_status;
        this.pay_status = pay_status;
        this.discount_amount = discount_amount;
        this.order_id = order_id;
        this.vendor_name = vendor_name;
        this.payment_mode = payment_mode;
        this.payment_id = payment_id;
        this.sign = sign;
    }

    public v_order_user() {
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<v_order_item_user> getOrders() {
        return orders;
    }

    public void setOrders(List<v_order_item_user> orders) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
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
