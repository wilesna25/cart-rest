package com.example.cartrest.repository;

import com.example.cartrest.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
@RepositoryRestResource
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
