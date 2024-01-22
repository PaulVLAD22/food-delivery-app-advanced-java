package com.example.vladfood.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderDto {
    String customerUsername;
    String restaurantName;
    List<OrderItemDto> orderItems;

}
