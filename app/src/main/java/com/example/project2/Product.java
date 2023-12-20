package com.example.project2;

public class Product {
    private String title;
    private int image;
    private String tag;
    private double price;



    private String description;

    private int description_image;

    public Product(String title, int image, String tag, double price, String description, int description_image) {
        this.title = title;
        this.image = image;
        this.tag = tag;
        this.price = price;
        this.description = description;
        this.description_image = description_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDescription_image() {
        return description_image;
    }

    public void setDescription_image(int description_image) {
        this.description_image = description_image;
    }
}
