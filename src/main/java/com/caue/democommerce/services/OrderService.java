package com.caue.democommerce.services;

import com.caue.democommerce.dto.OrderDTO;
import com.caue.democommerce.entities.Order;
import com.caue.democommerce.entities.OrderItem;
import com.caue.democommerce.repositories.OrderRepository;
import com.caue.democommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long orderId) {

        Order entity = repository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return new OrderDTO(entity);
    }
}
