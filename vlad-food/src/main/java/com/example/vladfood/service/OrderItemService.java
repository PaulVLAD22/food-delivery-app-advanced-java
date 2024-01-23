package com.example.vladfood.service;

import com.example.vladfood.model.OrderItem;
import com.example.vladfood.model.Restaurant;
import com.example.vladfood.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;


}
