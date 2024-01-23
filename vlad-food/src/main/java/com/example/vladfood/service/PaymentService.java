package com.example.vladfood.service;

import com.example.vladfood.model.Order;
import com.example.vladfood.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PaymentService {
    private final ExecutorService paymentThreadPool = Executors.newFixedThreadPool(5);

    private int calculateOrderPrice(Order order) {
        int price = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            price += orderItem.getMenuItem().getPrice() * orderItem.getQuantity();
        }
        return price;

    }

    public void processPayment(Order order) {
        if (order.getStatus() == Order.OrderStatus.NEW) {
            // Submit a payment processing task to the thread pool
            paymentThreadPool.submit(() -> {
                try {
                    Thread.sleep(5000);
                    // Simulate payment processing time equal to order price
//                    Thread.sleep(calculateOrderPrice(order));

                    // Update order status to IN_PROGRESS
                    order.setOrderStatus(Order.OrderStatus.IN_PROGRESS);

                    // You can perform additional actions here after payment is processed

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }


}

