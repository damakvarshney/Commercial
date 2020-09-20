package com.commercial.after_login.retailer;

public class r_feedback_user {
    String date;
    long feedback_id;
    String subject;
    String desc;
    String name;

    public r_feedback_user(String name,String date,String subject, String desc) {
        this.name=name;
        this.date = date;
        feedback_id= System.currentTimeMillis();
        this.subject = subject;
        this.desc = desc;
    }

    public r_feedback_user() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getfeedback_id() {
        return feedback_id;
    }

    public void setfeedback_id(long feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
