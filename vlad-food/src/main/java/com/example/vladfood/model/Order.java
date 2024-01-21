package com.example.vladfood.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderTime;

    private LocalDateTime deliveryTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setOrderStatus(OrderStatus newStatus) {
        this.status = newStatus;

        // Notify observers when the order status changes
        notifyObservers();
    }

    // Assuming OrderStatus is an enum (NEW, IN_PROGRESS, DELIVERED, CANCELED)
    public enum OrderStatus {
        NEW, IN_PROGRESS, DELIVERED, CANCELED
    }

    @Column(nullable = false)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Setter
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "deliverer_id")
    private User deliverer;

    // ... Other getters and setters ...


    // observer
    public void notifyObservers() {
        // Notify the customer and deliverer (observers) based on the order status
        if (status == OrderStatus.IN_PROGRESS || status == OrderStatus.CANCELED || status == OrderStatus.DELIVERED) {
            // Notify the customer
            customer.sendOrderStatusUpdate(this.getStatus());

            if (deliverer != null) {
                // Notify the deliverer
                deliverer.sendOrderStatusUpdate(this.getStatus());
            }
        }
    }

}
