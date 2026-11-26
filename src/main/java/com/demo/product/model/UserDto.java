package com.demo.product.model;

// MODEL - CONSUMER copy of user-service's contract (intentionally NOT shared).
public record UserDto(Long id, int name, String email) {}
