package com.demo.product.service;

import com.demo.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductServiceTest {

    private final ProductService service = new ProductService(new ProductRepository());

    @Test
    void getAllReturnsTwoItems() {
        assertEquals(2, service.getAll().size());
    }

    @Test
    void getByIdFindsExistingItem() {
        assertTrue(service.getById(500L).isPresent());
    }

    @Test
    void getByIdReturnsEmptyForUnknownId() {
        assertTrue(service.getById(999999L).isEmpty());
    }
}
