package com.example.project2;

public class Cart {
    private String productTitle;
    private String productTag;
    private double productPrice;
    private int shoeSize;
    private int quantity;

    private int productImage;

    public Cart(String productTitle, String productTag, double productPrice, int shoeSize, int quantity, int productImage) {
        this.productTitle = productTitle;
        this.productTag = productTag;
        this.productPrice = productPrice;
        this.shoeSize = shoeSize;
        this.quantity = quantity;
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTag() {
        return productTag;
    }

    public void setProductTag(String productTag) {
        this.productTag = productTag;
    }
}
