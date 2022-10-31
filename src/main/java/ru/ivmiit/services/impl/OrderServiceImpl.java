package ru.ivmiit.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.ivmiit.dto.request.OrderRequest;
import ru.ivmiit.dto.response.OrderResponse;
import ru.ivmiit.exceptions.order.OrderNotFoundException;
import ru.ivmiit.mappers.OrderMapper;
import ru.ivmiit.models.OrderEntity;
import ru.ivmiit.models.UserEntity;
import ru.ivmiit.repositories.OrderRepository;
import ru.ivmiit.repositories.UserRepository;
import ru.ivmiit.services.OrderService;
import ru.ivmiit.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final ProductService productService;

    @Override
    public List<OrderResponse> getAllUserOrders() {
        return orderRepository.findAllByCustomer(getCurrentUser())
                .stream().map(orderMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse getUserOrder(Long id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        if(!order.getCustomer().getId().equals(getCurrentUser().getId())) {
            throw new OrderNotFoundException();
        }

        return orderMapper.toResponse(order);
    }

    @Override
    public OrderResponse addOrder(OrderRequest request) {
        OrderEntity order = orderMapper.toEntity(request);
        order.setCustomer(getCurrentUser());
        order.setProduct(productService.findProductEntityById(request.getProductId()));
        return orderMapper.toResponse(orderRepository.save(order));
    }

    private UserEntity getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername()).get();
    }
}
