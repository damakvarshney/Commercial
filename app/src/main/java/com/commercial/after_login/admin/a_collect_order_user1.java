package com.commercial.after_login.admin;

import android.widget.Button;

public class a_collect_order_user1 {
    String date,name,status;
    Button view_button;
    long order_id;

    public a_collect_order_user1(long order_id, String date, String name, String status) {
        this.date = date;
        this.name = name;
        this.status = status;
        this.order_id=order_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public a_collect_order_user1() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Button getView_button() {
        return view_button;
    }

    public void setView_button(Button view_button) {
        this.view_button = view_button;
    }
}
