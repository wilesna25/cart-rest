package com.example.cartrest.service;

import com.example.cartrest.model.*;
import com.example.cartrest.repository.CartProductRepository;
import com.example.cartrest.repository.CartRepository;
import com.example.cartrest.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartProductService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartProductRepository;


    @Transactional
    public String addProductToCart(int cartId, int productId, int quantity) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalCart.isPresent() && optionalProduct.isPresent()) {
            Cart cart = optionalCart.get();
            Product product = optionalProduct.get();

            CartProductId cartProductId = new CartProductId();
            cartProductId.setCartId(cart.getId());
            cartProductId.setProductId(product.getId());

            // Verify if CartProduct  exists
            Optional<CartProduct> optionalCartProduct = cartProductRepository.findById(cartProductId);

            CartProduct cartProduct;
            if (optionalCartProduct.isPresent()) {
                // If exists, update the quantity
                System.out.println("updating new product quantity .....");
                cartProduct = optionalCartProduct.get();
                cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
            } else {
                // If not exists, create a new CartProduct
                System.out.println("Creating a new CartProduct.....");
                cartProduct = new CartProduct();
                cartProduct.setId(cartProductId);
                cartProduct.setCart(cart);
                cartProduct.setProduct(product);
                cartProduct.setQuantity(quantity);
            }

            // Save/Update new CartProduct
            cartProductRepository.save(cartProduct);

            return "Product added to cart with updated quantity " + cartProduct.getQuantity();
        } else {
            return "Cart or Product don´t exist on db";
        }
    }

    public String deleteProductFromCart(int productId) {

        CartProductId cartProductId = new CartProductId();
        cartProductId.setCartId(1);
        cartProductId.setProductId(productId);

        // verify if CartProduct exists
        if (cartProductRepository.existsById(cartProductId)) {
            cartProductRepository.deleteById(cartProductId);
            return "Product deleted successfully.";
        } else {
            return "Product not found in the cart.";
        }
    }

    public String calculateCartTotal(int cartId) {
        List<CartProduct> cartProducts = cartProductRepository.findByCartId(cartId);
        Integer total = 0;

        for (CartProduct cartProduct : cartProducts) {
            int productPrice = cartProduct.getProduct().getPrice();
            int quantity = cartProduct.getQuantity();
            total = total + (productPrice * quantity);
        }

        return "The total cart cost is : " + total;

    }

    public CartDTO viewCart(int cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new IllegalArgumentException("Cart not found with ID " + cartId);
        }

        List<CartProduct> cartProducts = cartProductRepository.findByCartId(cartId);

        List<CartProductDTO> productDtos = cartProducts.stream()
                .map(cartProduct -> new CartProductDTO(
                        cartProduct.getProduct().getId(),
                        cartProduct.getProduct().getProductName(),
                        cartProduct.getProduct().getPrice(),
                        cartProduct.getQuantity()
                ))
                .collect(Collectors.toList());

        Integer total = 0;

        for (CartProduct cartProduct : cartProducts) {
            int productPrice = cartProduct.getProduct().getPrice();
            int quantity = cartProduct.getQuantity();
            total = total + (productPrice * quantity);
        }

        return new CartDTO(cartId, productDtos, total);

    }

    public String updateProductQuantity(int cartId, int productId, int quantity) {

        CartProductId cartProductId = new CartProductId(cartId, productId);
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));

        cartProduct.setQuantity(quantity);

        if(quantity>0){
            cartProductRepository.save(cartProduct);
            return "updated product quantity to " + quantity;
        }else{
            cartProductRepository.delete(cartProduct);
            return "quantity = 0,  product deleted from cart";
        }

    }
}
