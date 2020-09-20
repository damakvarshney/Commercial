package com.commercial.after_login.admin;

public class a_give_access_User {
    String consumer_name;
    String consumer_id;

    public a_give_access_User(String consumer_name, String consumer_id) {
        this.consumer_name = consumer_name;
        this.consumer_id = consumer_id;
    }

    public String getConsumer_name() {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name = consumer_name;
    }

    public String getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(String consumer_id) {
        this.consumer_id = consumer_id;
    }
}
