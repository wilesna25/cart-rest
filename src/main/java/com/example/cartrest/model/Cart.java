package com.example.cartrest.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.springframework.data.annotation.Id;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cartName;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartProduct> cartProducts = new HashSet<>();

    public Cart() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public Set<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Set<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Cart(Integer id, String cartName) {
        this.id = id;
        this.cartName = cartName;
    }

    // Getters, setters, etc.
}



