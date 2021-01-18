package com.akademiaspring.pd2.dto;

import com.akademiaspring.pd2.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class Basket {

    private List<Product> productsInBasket;

    public Basket() {
        this.productsInBasket = new ArrayList<>();
    }

    public void addToBasket(Product product){
        productsInBasket.add(product);
    }

    public BigDecimal getBasketTotalSum(){
        return productsInBasket.stream()
                .map(p -> p.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
