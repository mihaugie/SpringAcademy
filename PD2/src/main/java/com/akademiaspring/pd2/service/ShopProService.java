package com.akademiaspring.pd2.service;

import com.akademiaspring.pd2.dto.Basket;
import com.akademiaspring.pd2.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
@Profile("pro")
public class ShopProService {

    private List<Product> productList;
    private Basket basket;
    @Value("${vat}")
    private double vat;
    @Value("${rebate}")
    private double rebate;
    private int MINPRICE = 50;
    private int MAXPRICE = 300;

    public ShopProService() {
        productList = new ArrayList<>();
        Product product1 = new Product(1, "Product_1", generateRandomPrice());
        Product product2 = new Product(2, "Product_2", generateRandomPrice());
        Product product3 = new Product(3, "Product_3", generateRandomPrice());
        Product product4 = new Product(4, "Product_4", generateRandomPrice());
        Product product5 = new Product(5, "Product_5", new BigDecimal(100));
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runShop() {
        printBill();
        runBasket();
    }

    public BigDecimal generateRandomPrice() {
        Random randomPrice = new Random();
        return BigDecimal.valueOf((randomPrice.nextInt(MAXPRICE - MINPRICE)) + MINPRICE);
    }

    private void printBill() {
        calculatePricesWithVatAndRebate();
        productList.forEach(p -> System.out.println(p));
    }

    private void calculatePricesWithVatAndRebate() {
        productList.forEach(p -> p.setPrice(p.getPrice().multiply(BigDecimal.ONE.add(BigDecimal.valueOf(vat)))));
        productList.forEach(p -> p.setPrice(p.getPrice().multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(rebate)))));
    }

    public void runBasket() {
        basket = new Basket();
        int shouldContinue = 1;
        while (shouldContinue == 1) {
            System.out.println("Please write a number of a product you want to add to the basket.");
            Scanner scanner = new Scanner(System.in);
            int idOfProductToAdd = scanner.nextInt();
            Product selectedProduct = productList.get(idOfProductToAdd - 1);

            basket.addToBasket(selectedProduct);
            System.out.printf("%s was added to basket. Total sum of a basket: %f \n", selectedProduct.getProductName(), basket.getBasketTotalSum());

            System.out.println("Press 1 if you want to continue shopping.");
            Scanner scanner1 = new Scanner(System.in);
            shouldContinue = scanner1.nextInt();
        }
        System.out.println("You have left shopping cart");
    }

}
