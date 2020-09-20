package com.commercial.before_login;


public class register_vendor_user {
    String cart_no, vendor_id, vendor_name, vendor_address, vendor_gst_no, vendor_email_id, vendor_password;
    boolean status = false;
    int discount = 0;
    long vendor_mobile_no;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public register_vendor_user(String date) {
        this.date = date;
    }

    public register_vendor_user(String cart_no, String vendor_id, String vendor_name, String vendor_address, String vendor_gst_no, String vendor_email_id, String vendor_password, long vendor_mobile_no, String date) {
        this.cart_no = cart_no;
        this.vendor_id = vendor_id;
        this.vendor_name = vendor_name;
        this.vendor_address = vendor_address;
        this.vendor_gst_no = vendor_gst_no;
        this.vendor_email_id = vendor_email_id;
        this.vendor_password = vendor_password;
        this.vendor_mobile_no = vendor_mobile_no;
        this.date = date;
    }
    public register_vendor_user(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }



    public register_vendor_user() {
    }

    public String getCart_no() {
        return cart_no;
    }

    public void setCart_no(String cart_no) {
        this.cart_no = cart_no;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_address() {
        return vendor_address;
    }

    public void setVendor_address(String vendor_address) {
        this.vendor_address = vendor_address;
    }

    public String getVendor_gst_no() {
        return vendor_gst_no;
    }

    public void setVendor_gst_no(String vendor_gst_no) {
        this.vendor_gst_no = vendor_gst_no;
    }

    public String getVendor_email_id() {
        return vendor_email_id;
    }

    public void setVendor_email_id(String vendor_email_id) {
        this.vendor_email_id = vendor_email_id;
    }

    public String getVendor_password() {
        return vendor_password;
    }

    public void setVendor_password(String vendor_password) {
        this.vendor_password = vendor_password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getVendor_mobile_no() {
        return vendor_mobile_no;
    }

    public void setVendor_mobile_no(long vendor_mobile_no) {
        this.vendor_mobile_no = vendor_mobile_no;
    }
}