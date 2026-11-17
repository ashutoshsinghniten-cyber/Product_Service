package com.demo.product.service;

import com.demo.product.model.ProductDto;
import com.demo.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Business logic of this service's own data
@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDto> getAll() {
        return repository.findAll();
    }

    public Optional<ProductDto> getById(Long id) {
        return repository.findById(id);
    }
}
