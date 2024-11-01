package com.example.cartrest.controller;

import com.example.cartrest.model.*;
import com.example.cartrest.repository.CartProductRepository;
import com.example.cartrest.repository.CartRepository;
import com.example.cartrest.repository.ProductRepository;
import com.example.cartrest.service.CartProductService;
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
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //@GetMapping("/cart")
    public Cart getCartById(@RequestParam int id){
        return cartService.getCartById(id);
    }

    @Autowired
    private CartProductService cartProductService;


    /**
     * Adds a product to the specified cart. If the product already exists in the cart,
     * it updates the quantity of that product.
     *
     * @param cartId   the ID of the cart to which the product will be added
     * @param productId the ID of the product to be added to the cart
     * @param quantity  the quantity of the product to be added
     * @return a message indicating the result of the operation
     */
    @PostMapping("/{cartId}/addProduct/{productId}")
    public String addProductToCart(
            @PathVariable int cartId,
            @PathVariable int productId,
            @RequestParam int quantity) {

        // Add or update product in cart
        return cartProductService.addProductToCart(cartId, productId, quantity);

    }

    /**
     * Deletes a product from the specified cart.
     *
     * @param productId the ID of the product to be deleted from the cart
     * @return a message indicating the result of the operation
     */
    @PostMapping("/{cartId}/delete/{productId}")
    public String deleteProductFromCart(@PathVariable int productId) {
            return cartProductService.deleteProductFromCart(productId);
    }

    /**
     * Retrieves the total price of all products in the specified cart.
     *
     * @param cartId the ID of the cart for which to calculate the total price
     * @return a string representing the total price of products in the cart
     */
    @GetMapping("/{cartId}/totalPrice")
    public String getCartTotalPrice(@PathVariable int cartId) {
        return cartProductService.calculateCartTotal(cartId);
    }

    /**
     * Views the contents of the specified cart, including all products and their details.
     *
     * @param cartId the ID of the cart to be viewed
     * @return a CartDTO object representing the cart's contents
     */
    @GetMapping("/{cartId}")
    public CartDTO viewCart(@PathVariable Integer cartId) {
        return cartProductService.viewCart(cartId);
    }

    /**
     * Updates the quantity of a specific product in the specified cart. If quantity = 0 product will be removed
     *
     * @param cartId     the ID of the cart containing the product
     * @param product_id  the ID of the product whose quantity is to be updated
     * @param quantity    the new quantity of the product
     * @return a message indicating the result of the operation
     */
    @PutMapping("/{cartId}/updateProductQuantity")
    public String updateProductQuantity(@PathVariable int cartId, @RequestParam int product_id , @RequestParam int quantity) {
        return cartProductService.updateProductQuantity(cartId, product_id, quantity);
    }
}
