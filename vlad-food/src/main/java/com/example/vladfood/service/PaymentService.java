package com.example.vladfood.service;

import com.example.vladfood.model.Order;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PaymentService {
    private final ExecutorService paymentThreadPool = Executors.newFixedThreadPool(5);

    public void processPayment(Order order) {
        if (order.getStatus() == Order.OrderStatus.NEW) {
            // Submit a payment processing task to the thread pool
            paymentThreadPool.submit(() -> {
                try {
                    // Simulate payment processing time (2 minutes)
                    Thread.sleep(120000);

                    // Update order status to IN_PROGRESS
                    order.setOrderStatus(Order.OrderStatus.IN_PROGRESS);

                    // You can perform additional actions here after payment is processed

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    public void shutdown() {
        // Shutdown the thread pool gracefully
        paymentThreadPool.shutdown();
        try {
            if (!paymentThreadPool.awaitTermination(10, java.util.concurrent.TimeUnit)) {
                paymentThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            paymentThreadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

