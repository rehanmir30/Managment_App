package com.example.managmentapp.Model;

public class Purchase {
    String name,time,quantity;

    public Purchase(String name, String time, String quantity) {
        this.name = name;
        this.time = time;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
