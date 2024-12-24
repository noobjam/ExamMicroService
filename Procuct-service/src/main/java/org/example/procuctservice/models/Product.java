package org.example.procuctservice.models;

import jakarta.persistence.Entity;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int stock;
    private double price;

    // Getters and Setters
}

