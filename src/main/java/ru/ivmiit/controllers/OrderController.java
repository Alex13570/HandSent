package ru.ivmiit.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.dto.request.OrderRequest;
import ru.ivmiit.dto.response.OrderResponse;
import ru.ivmiit.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderResponse> getAllUserOrders() {
        return orderService.getAllUserOrders();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getUserOrder(id);
    }

    @PostMapping
    public OrderResponse addOrder(@RequestBody OrderRequest request) {
        return orderService.addOrder(request);
    }
}
