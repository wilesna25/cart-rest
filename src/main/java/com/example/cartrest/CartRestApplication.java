package com.example.cartrest;

import com.example.cartrest.model.Cart;
import com.example.cartrest.model.Product;
import com.example.cartrest.repository.CartRepository;
import com.example.cartrest.repository.ProductRepository;
import jakarta.xml.bind.SchemaOutputResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CartRestApplication implements CommandLineRunner {


    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(CartRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Cart cart = new Cart(1, "cartW");

        cartRepository.save(cart);

        Product product1 = new Product("product1", 10);
        Product product2 = new Product( "product2", 20);
        Product product3 = new Product("product3", 30);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }
}
