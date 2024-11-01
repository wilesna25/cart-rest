package com.example.cartrest.controller;

import com.example.cartrest.model.Cart;
import com.example.cartrest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //@GetMapping("/cart")
    public Cart getCartById(@RequestParam int id){
        return cartService.getCartById(id);
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart pCart){
        Cart savedCart = cartService.saveCart(pCart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);

    }


}
