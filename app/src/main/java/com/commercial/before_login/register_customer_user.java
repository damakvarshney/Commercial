package com.commercial.before_login;

public class register_customer_user {
    String customer_id, customer_name, customer_address,  customer_gst_no, customer_email_id, customer_password;
    Long customer_mobile_no;
    String customer_date;
    public register_customer_user() {
    }

    public register_customer_user(String customer_id, String customer_name, String customer_address, String customer_gst_no, String customer_email_id, String customer_password, Long customer_mobile_no, String customer_date) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_gst_no = customer_gst_no;
        this.customer_email_id = customer_email_id;
        this.customer_password = customer_password;
        this.customer_mobile_no = customer_mobile_no;
        this.customer_date = customer_date;
    }

    public String getCustomer_date() {
        return customer_date;
    }

    public void setCustomer_date(String customer_date) {
        this.customer_date = customer_date;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_gst_no() {
        return customer_gst_no;
    }

    public void setCustomer_gst_no(String customer_gst_no) {
        this.customer_gst_no = customer_gst_no;
    }

    public String getCustomer_email_id() {
        return customer_email_id;
    }

    public void setCustomer_email_id(String customer_email_id) {
        this.customer_email_id = customer_email_id;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    public Long getCustomer_mobile_no() {
        return customer_mobile_no;
    }

    public void setCustomer_mobile_no(Long customer_mobile_no) {
        this.customer_mobile_no = customer_mobile_no;
    }
}
