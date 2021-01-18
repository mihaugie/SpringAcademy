package com.akademiaspring.pd2.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private int id;
    private String productName;
    private BigDecimal price;

    public Product() {
    }

    public Product(int id, String productName, BigDecimal price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "id=" + id +" -- "+ productName + '\'' +
                ", price=" + price;
    }
}
