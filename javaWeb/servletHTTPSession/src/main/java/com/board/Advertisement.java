package com.board;

public class Advertisement {
    private String description;
    private Double price;

    public Advertisement(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Advertisement [Description: " + description + ", Price: " + price + "]";
    }
}
