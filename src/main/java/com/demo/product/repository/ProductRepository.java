package com.demo.product.repository;

import com.demo.product.model.ProductDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// R of data layer: in-memory data (replace with a real DB later)
@Repository
public class ProductRepository {

    private final List<ProductDto> data = List.of(
            new ProductDto(500L, "Laptop", 55000.0),
            new ProductDto(501L, "Phone", 20000.0));

    public List<ProductDto> findAll() {
        return data;
    }

    public Optional<ProductDto> findById(Long id) {
        return data.stream().filter(i -> i.id().equals(id)).findFirst();
    }
}
