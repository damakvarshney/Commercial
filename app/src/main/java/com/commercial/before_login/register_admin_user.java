package com.commercial.before_login;

public class register_admin_user {
    String emp_type, emp_id, emp_name, emp_address, emp_gst_no, emp_password;
    long emp_mobile_no;
    String emp_email_id;
    String date;

    public register_admin_user(String emp_type, String emp_id, String emp_name, String emp_address, String emp_gst_no, String emp_password, long emp_mobile_no, String emp_email_id, String date) {
        this.emp_type = emp_type;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_address = emp_address;
        this.emp_gst_no = emp_gst_no;
        this.emp_password = emp_password;
        this.emp_mobile_no = emp_mobile_no;
        this.emp_email_id = emp_email_id;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public register_admin_user() {
    }



    public String getEmp_type() {
        return emp_type;
    }

    public void setEmp_type(String emp_type) {
        this.emp_type = emp_type;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }

    public String getEmp_gst_no() {
        return emp_gst_no;
    }

    public void setEmp_gst_no(String emp_gst_no) {
        this.emp_gst_no = emp_gst_no;
    }

    public String getEmp_password() {
        return emp_password;
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }

    public long getEmp_mobile_no() {
        return emp_mobile_no;
    }

    public void setEmp_mobile_no(long emp_mobile_no) {
        this.emp_mobile_no = emp_mobile_no;
    }

    public String getEmp_email_id() {
        return emp_email_id;
    }

    public void setEmp_email_id(String emp_email_id) {
        this.emp_email_id = emp_email_id;
    }
}