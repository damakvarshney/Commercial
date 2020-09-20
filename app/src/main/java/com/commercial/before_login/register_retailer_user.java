package com.commercial.before_login;


public class register_retailer_user {
    String r_consumer_id;
    String r_shoppe_type;
    String r_shop_name;
    String r_owner_name;
    String r_address;
    Long r_mobile_no;
    String r_gst_no;
    String r_email_id;
    String r_password;
    String date;
    int discount=0;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public register_retailer_user(String r_consumer_id, String r_shoppe_type, String r_shop_name, String r_owner_name, String r_address, Long r_mobile_no, String r_gst_no, String r_email_id, String r_password, String date) {
        this.r_consumer_id = r_consumer_id;
        this.r_shoppe_type = r_shoppe_type;
        this.r_shop_name = r_shop_name;
        this.r_owner_name = r_owner_name;
        this.r_address = r_address;
        this.r_mobile_no = r_mobile_no;
        this.r_gst_no = r_gst_no;
        this.r_email_id = r_email_id;
        this.r_password = r_password;
        this.date = date;
    }

    public register_retailer_user(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    boolean status=false;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public register_retailer_user() {
    }

    public String getR_consumer_id() {
        return r_consumer_id;
    }

    public void setR_consumer_id(String r_consumer_id) {
        this.r_consumer_id = r_consumer_id;
    }

    public String getR_shoppe_type() {
        return r_shoppe_type;
    }

    public void setR_shoppe_type(String r_shoppe_type) {
        this.r_shoppe_type = r_shoppe_type;
    }

    public String getR_shop_name() {
        return r_shop_name;
    }

    public void setR_shop_name(String r_shop_name) {
        this.r_shop_name = r_shop_name;
    }

    public String getR_owner_name() {
        return r_owner_name;
    }

    public void setR_owner_name(String r_owner_name) {
        this.r_owner_name = r_owner_name;
    }

    public String getR_address() {
        return r_address;
    }

    public void setR_address(String r_address) {
        this.r_address = r_address;
    }

    public Long getR_mobile_no() {
        return r_mobile_no;
    }

    public void setR_mobile_no(Long r_mobile_no) {
        this.r_mobile_no = r_mobile_no;
    }

    public String getR_gst_no() {
        return r_gst_no;
    }

    public void setR_gst_no(String r_gst_no) {
        this.r_gst_no = r_gst_no;
    }

    public String getR_email_id() {
        return r_email_id;
    }

    public void setR_email_id(String r_email_id) {
        this.r_email_id = r_email_id;
    }

    public String getR_password() {
        return r_password;
    }

    public void setR_password(String r_password) {
        this.r_password = r_password;
    }


}
