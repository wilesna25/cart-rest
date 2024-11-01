package com.example.cartrest.service;

import com.example.cartrest.model.Cart;
import com.example.cartrest.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCartById(int id){
        return new Cart(1, 1);
    }

}
