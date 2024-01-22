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
        // Initiate a graceful shutdown
        paymentThreadPool.shutdown();

        try {
            // Wait for a period to allow existing tasks to complete
            Thread.sleep(60000); // 60 seconds

            if (!paymentThreadPool.isTerminated()) {
                // Attempt to stop all actively executing tasks
                paymentThreadPool.shutdownNow();

                // Additional waiting period for tasks to respond to cancellation
                Thread.sleep(60000); // 60 seconds

                if (!paymentThreadPool.isTerminated()) {
                    System.err.println("Payment thread pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if the current thread also interrupted
            paymentThreadPool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}

