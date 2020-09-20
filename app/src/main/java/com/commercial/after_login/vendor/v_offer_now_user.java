package com.commercial.after_login.vendor;
public class v_offer_now_user {

   String offer_name,from_date,till_date;

    public v_offer_now_user(String offer_name, String from_date, String till_date) {
        this.offer_name = offer_name;
        this.from_date = from_date;
        this.till_date = till_date;
    }

    public v_offer_now_user() {
    }

    public String getOffer_name() {
        return offer_name;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTill_date() {
        return till_date;
    }

    public void setTill_date(String till_date) {
        this.till_date = till_date;
    }
}
