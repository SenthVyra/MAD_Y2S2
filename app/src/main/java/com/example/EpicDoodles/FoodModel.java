package com.example.EpicDoodles;

public class FoodModel {

    String url,name,type,description,price;

    FoodModel(){}

    FoodModel(String url, String name, String type, String description, String price){
        this.url = url;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
