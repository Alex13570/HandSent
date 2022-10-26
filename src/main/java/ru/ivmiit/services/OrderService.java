package ru.ivmiit.services;

import ru.ivmiit.dto.request.OrderRequest;
import ru.ivmiit.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllUserOrders();
    OrderResponse getUserOrder(Long id);
    OrderResponse addOrder(OrderRequest order);
}
