package com.example.cartrest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.DataAmount;
import lombok.Data;

//import org.springframework.data.annotation.Id;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private int id ;
    private int value;
    private int products_quantity;


    public Cart() {

    }

    public Cart(int value, int products_quantity) {
        this.value = value;
        this.products_quantity = products_quantity;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
