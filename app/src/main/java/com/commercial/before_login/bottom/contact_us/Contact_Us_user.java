package com.commercial.before_login.bottom.contact_us;

public class Contact_Us_user {
    String name,address,email_id,description;
    long mobile_no;
    long feedback_id;
    String date;
    String subject;

    public Contact_Us_user(String name, String address, String email_id, String description, long mobile_no, String date, String subject) {
        this.name = name;
        this.address = address;
        this.email_id = email_id;
        this.description = description;
        this.mobile_no = mobile_no;
        this.date = date;
        this.subject = subject;
        feedback_id = System.currentTimeMillis();
    }

    public Contact_Us_user() {
    }

    public long getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(long feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(long mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
