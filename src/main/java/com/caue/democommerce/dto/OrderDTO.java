package com.caue.democommerce.dto;

import com.caue.democommerce.entities.Order;
import com.caue.democommerce.entities.OrderItem;
import com.caue.democommerce.enums.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;

    private List<OrderItemDTO> items = new ArrayList<>();


    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        client = new ClientDTO(entity.getClient());
        payment = entity.getPayment() == null ? null : new PaymentDTO(entity.getPayment());

        for (OrderItem orderItem : entity.getItems()) {

            items.add(new OrderItemDTO(orderItem));
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public double getTotal() {
        return items.stream().map(OrderItemDTO::getSubTotal).reduce(0.0, Double::sum);
    }
}
