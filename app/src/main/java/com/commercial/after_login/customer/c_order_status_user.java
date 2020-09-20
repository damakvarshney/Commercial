package com.commercial.after_login.customer;


public class c_order_status_user  {
    long order_id;
    String date;
    int quantity,amount;

    public c_order_status_user(long order_id, String date, int quantity, int amount) {
        this.order_id = order_id;
        this.date = date;
        this.quantity = quantity;
        this.amount = amount;
    }

    public c_order_status_user() {
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
