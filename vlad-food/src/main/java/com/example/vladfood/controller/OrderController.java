package com.example.vladfood.controller;

import com.example.vladfood.dto.OrderDto;
import com.example.vladfood.model.Order;
import com.example.vladfood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto) {
        System.out.println("New order");
        orderService.createOrder(orderDto);
    }

    @PutMapping
    public void updateOrder(long orderId, Order.OrderStatus orderStatus){
        orderService.updateOrder(orderId, orderStatus);
    }

}
