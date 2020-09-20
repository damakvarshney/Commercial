package com.commercial.after_login.admin;

public class a_update_pay_user {
    long order_id;
    String date,status;
    int amount;

    public a_update_pay_user(long order_id, String date, String status, int amount) {
        this.order_id = order_id;
        this.date = date;
        this.status = status;
        this.amount = amount;
    }

    public a_update_pay_user() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
