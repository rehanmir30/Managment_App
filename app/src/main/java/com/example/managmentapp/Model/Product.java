package com.example.managmentapp.Model;

public class Product {
    String p_name,p_price,p_description,p_image;

    public Product(String p_name, String p_price, String p_description, String p_image) {
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_description = p_description;
        this.p_image = p_image;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public String getP_name() {
        return p_name;
    }

    public String getP_price() {
        return p_price;
    }

    public String getP_description() {
        return p_description;
    }

    public String getP_image() {
        return p_image;
    }
}
