package com.programrabbit.adsmart;

public class Advertisement {
    String title;
    String description;
    String price;
    String photo_url;

    public Advertisement(String title, String description, String price, String photo_url) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.photo_url = photo_url;
    }

    public Advertisement(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
