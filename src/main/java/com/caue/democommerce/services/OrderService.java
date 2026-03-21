package com.caue.democommerce.services;

import com.caue.democommerce.dto.OrderDTO;
import com.caue.democommerce.dto.OrderItemDTO;
import com.caue.democommerce.entities.Order;
import com.caue.democommerce.entities.OrderItem;
import com.caue.democommerce.entities.Product;
import com.caue.democommerce.entities.User;
import com.caue.democommerce.enums.OrderStatus;
import com.caue.democommerce.repositories.OrderItemRepository;
import com.caue.democommerce.repositories.OrderRepository;
import com.caue.democommerce.repositories.ProductRepository;
import com.caue.democommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository repository, UserService userService, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.repository = repository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long orderId) {

        Order entity = repository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return new OrderDTO(entity);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO orderItem: dto.getItems()){
            Product product = productRepository.getReferenceById(orderItem.getProductId());

            order.getItems().add(new OrderItem(order, product,orderItem.getQuantity(),product.getPrice()));

        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);

    }
}
