package com.commercial.after_login.admin;

public class a_update_offer_user {
    String offer_name,offer,from_date,till_date,offer_id;
    String offer_image;
    boolean for_retailer,for_customer,for_vendor;
    Long id;

    public a_update_offer_user(String offer_name, String offer, String from_date, String till_date, String offer_id, String offer_image, boolean for_retailer, boolean for_customer, boolean for_vendor) {
        this.offer_name = offer_name;
        this.offer = offer;
        this.from_date = from_date;
        this.till_date = till_date;
        this.offer_id = offer_id;
        this.offer_image = offer_image;
        this.for_retailer = for_retailer;
        this.for_customer = for_customer;
        this.for_vendor = for_vendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public a_update_offer_user() {
    }

    public String getOffer_name() {
        return offer_name;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
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

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getOffer_image() {
        return offer_image;
    }

    public void setOffer_image(String offer_image) {
        this.offer_image = offer_image;
    }

    public boolean isFor_retailer() {
        return for_retailer;
    }

    public void setFor_retailer(boolean for_retailer) {
        this.for_retailer = for_retailer;
    }

    public boolean isFor_customer() {
        return for_customer;
    }

    public void setFor_customer(boolean for_customer) {
        this.for_customer = for_customer;
    }

    public boolean isFor_vendor() {
        return for_vendor;
    }

    public void setFor_vendor(boolean for_vendor) {
        this.for_vendor = for_vendor;
    }


}
