package com.example.cartrest.repository;


import com.example.cartrest.model.Cart;
import com.example.cartrest.model.CartProduct;
import com.example.cartrest.model.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {

    List<CartProduct> findByCartId(Integer cartId);


}
