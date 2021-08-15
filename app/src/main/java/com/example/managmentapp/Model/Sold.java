package com.example.managmentapp.Model;

public class Sold {
    String s_name,s_price,s_quantity,s_time;

    public Sold(String s_name, String s_price, String s_quantity, String s_time) {
        this.s_name = s_name;
        this.s_price = s_price;
        this.s_quantity = s_quantity;
        this.s_time = s_time;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_price() {
        return s_price;
    }

    public void setS_price(String s_price) {
        this.s_price = s_price;
    }

    public String getS_quantity() {
        return s_quantity;
    }

    public void setS_quantity(String s_quantity) {
        this.s_quantity = s_quantity;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

}
