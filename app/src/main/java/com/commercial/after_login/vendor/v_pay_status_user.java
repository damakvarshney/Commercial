package com.commercial.after_login.vendor;


public class v_pay_status_user {
    long order_id;
    String date, state;
    int amount;

    public v_pay_status_user() {
    }

    public v_pay_status_user(long order_id, String date, String state, int amount) {
        this.order_id = order_id;
        this.date = date;
        this.state = state;
        this.amount = amount;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
