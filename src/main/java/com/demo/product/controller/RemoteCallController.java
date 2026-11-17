package com.demo.product.controller;

import com.demo.product.model.UserDto;
import com.demo.product.model.OrderDto;
import com.demo.product.service.RemoteCallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Endpoints that call the other two services (through RemoteCallService)
@RestController
@RequestMapping("/call")
public class RemoteCallController {

    private final RemoteCallService remote;

    public RemoteCallController(RemoteCallService remote) {
        this.remote = remote;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return remote.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return remote.getUserById(id);
    }

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {
        return remote.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return remote.getOrderById(id);
    }

    @GetMapping("/all")
    public Map<String, Object> callAll() {
        return remote.callAll();
    }
}
