package com.caue.democommerce.services;

import com.caue.democommerce.dto.OrderDTO;
import com.caue.democommerce.dto.OrderItemDTO;
import com.caue.democommerce.entities.Order;
import com.caue.democommerce.entities.OrderItem;
import com.caue.democommerce.entities.Product;
import com.caue.democommerce.entities.User;
import com.caue.democommerce.enums.OrderStatus;
import com.caue.democommerce.exceptions.ForbiddenException;
import com.caue.democommerce.repositories.OrderItemRepository;
import com.caue.democommerce.repositories.OrderRepository;
import com.caue.democommerce.repositories.ProductRepository;
import com.caue.democommerce.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final AuthService authService;

    public OrderService(OrderRepository repository, UserService userService, ProductRepository productRepository, OrderItemRepository orderItemRepository, AuthService authService) {
        this.repository = repository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long orderId) {

        Order order = repository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        authService.validateUserAccessToOrder(order.getClient().getId());

        return new OrderDTO(order);
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
