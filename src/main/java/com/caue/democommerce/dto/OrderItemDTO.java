package com.caue.democommerce.dto;

import com.caue.democommerce.entities.OrderItem;

public class OrderItemDTO {

    private Integer quantity;

    private Double price;

    private Long productId;

    private String name;


    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer quantity, Double price, Long productId, String name) {
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.name = name;
    }

    public OrderItemDTO(OrderItem entity) {
        this.quantity = entity.getQuantity();
        this.price = entity.getPrice();
        this.productId = entity.getProduct().getId();
        this.name = entity.getProduct().getName();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSubTotal() {
        return quantity * price;
    }


}
