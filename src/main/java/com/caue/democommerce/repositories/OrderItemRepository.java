package com.caue.democommerce.repositories;

import com.caue.democommerce.entities.OrderItem;
import com.caue.democommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
