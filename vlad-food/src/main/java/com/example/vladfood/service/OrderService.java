package com.example.vladfood.service;

import com.example.vladfood.dto.OrderDto;
import com.example.vladfood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    // generate a create order method
    public void createOrder(OrderDto order) {
        // TODO:: testeaza process payment
//        orderRepository.save(order);
//        paymentService.processPayment(order);
    }

}
