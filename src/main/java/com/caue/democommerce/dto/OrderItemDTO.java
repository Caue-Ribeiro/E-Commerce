package com.caue.democommerce.dto;

import com.caue.democommerce.entities.OrderItem;

public class OrderItemDTO {

    private Integer quantity;

    private Double price;

    private Long productId;

    private String name;

    private String imgUrl;


    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer quantity, Double price, Long productId, String name, String imgUrl) {
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public OrderItemDTO(OrderItem entity) {
        quantity = entity.getQuantity();
        price = entity.getProduct().getPrice();
        productId = entity.getProduct().getId();
        name = entity.getProduct().getName();
        imgUrl = entity.getProduct().getImgUrl();
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
