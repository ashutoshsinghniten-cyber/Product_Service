package com.demo.product.service;

import com.demo.product.model.UserDto;
import com.demo.product.model.OrderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Calls EVERY endpoint of the other two services using RestTemplate.
 * If any of them changes its URL / fields / return type, these calls fail.
 */
@Service
public class RemoteCallService {

    private final RestTemplate rest;
    private final String userUrl;
    private final String orderUrl;

    public RemoteCallService(RestTemplate rest, @Value("${user.service.url}") String userUrl, @Value("${order.service.url}") String orderUrl) {
        this.rest = rest;
        this.userUrl = userUrl;
        this.orderUrl = orderUrl;
    }

    // ---- calls user-service (port 8080) ----
    public List<UserDto> getAllUsers() {
        return requireBody(rest.exchange(userUrl + "/api/users", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserDto>>() {}).getBody(), "/api/users");
    }

    public UserDto getUserById(Long id) {
        return requireBody(rest.getForObject(userUrl + "/api/users/" + id, UserDto.class), "/api/users/" + id);
    }

    // ---- calls order-service (port 8081) ----
    public List<OrderDto> getAllOrders() {
        return requireBody(rest.exchange(orderUrl + "/api/orders", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<OrderDto>>() {}).getBody(), "/api/orders");
    }

    public OrderDto getOrderById(Long id) {
        return requireBody(rest.getForObject(orderUrl + "/api/orders/" + id, OrderDto.class), "/api/orders/" + id);
    }

    // An empty or "null" response body is also a broken contract
    private static <T> T requireBody(T body, String path) {
        if (body == null) {
            throw new RestClientException("Empty response body from " + path);
        }
        return body;
    }

    // ---- calls every endpoint of both other services ----
    public Map<String, Object> callAll() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("users", getAllUsers());
        result.put("orders", getAllOrders());
        result.put("userById", getUserById(1L));
        result.put("orderById", getOrderById(100L));
        return result;
    }
}
