package com.commercial.after_login.admin;

public class a_maintain_stock_user {
   String item_id,item;
   int quantity,rate,amount;

    public a_maintain_stock_user() {
    }

    public a_maintain_stock_user(String item_id, String item, int quantity, int rate, int amount) {
        this.item_id = item_id;
        this.item = item;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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
