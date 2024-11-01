package com.example.cartrest.model;

import java.util.List;

public class CartDTO {
    private int cartId;
    private List<CartProductDTO> products;
    private Integer totalPrice;

    public CartDTO(int cartId, List<CartProductDTO> products, Integer totalPrice) {
        this.cartId = cartId;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<CartProductDTO> products) {
        this.products = products;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}