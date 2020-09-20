package com.commercial.after_login.admin;


public class a_update_item_user {
    String image;
    String i_name;
    String i_price;

    public String getI_price() {
        return i_price;
    }

    public void setI_price(String i_price) {
        this.i_price = i_price;
    }

    long id;

    public a_update_item_user() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String geti_name() {
        return i_name;
    }

    public void seti_name(String i_name) {
        this.i_name = i_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public a_update_item_user(String image, String i_name,String i_price) {
        this.image = image;
        this.i_name = i_name;
        this.i_price = i_price;
        id=System.currentTimeMillis();
    }
}
