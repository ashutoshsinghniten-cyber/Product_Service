package com.demo.product.model;

// MODEL - PROVIDER contract (this service owns it). Changing it breaks the other two services.
public record ProductDto(Long id, String name, double price) {}
