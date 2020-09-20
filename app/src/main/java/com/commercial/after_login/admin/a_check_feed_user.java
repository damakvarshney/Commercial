package com.commercial.after_login.admin;

public class a_check_feed_user {
    String date,name,subject;

    public a_check_feed_user(String date, String name, String subject) {
        this.date = date;
        this.name = name;
        this.subject = subject;
    }

    public a_check_feed_user() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
