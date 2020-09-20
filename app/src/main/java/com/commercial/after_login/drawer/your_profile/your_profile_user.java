package com.commercial.after_login.drawer.your_profile;

public class your_profile_user {
    String  emp_id, emp_name, emp_address, emp_gst_no;
    long emp_mobile_no;
    String emp_email_id;

    public your_profile_user(String emp_id, String emp_name, String emp_address, String emp_gst_no, long emp_mobile_no, String emp_email_id) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_address = emp_address;
        this.emp_gst_no = emp_gst_no;
        this.emp_mobile_no = emp_mobile_no;
        this.emp_email_id = emp_email_id;
    }

    public your_profile_user() {
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
