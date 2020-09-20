package com.commercial.after_login.admin;

public class a_collect_inside_user {
    String item;
    int quantity,rate,amount;

    public a_collect_inside_user(String item, int quantity, int rate, int amount) {
        this.item = item;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
    }

    public a_collect_inside_user() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
