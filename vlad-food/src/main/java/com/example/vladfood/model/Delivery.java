package com.example.vladfood.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id", nullable = false)
    private User deliveryPerson;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    // Assuming DeliveryStatus is an enum (PENDING, OUT_FOR_DELIVERY, DELIVERED)
    public enum DeliveryStatus {
        PENDING, OUT_FOR_DELIVERY, DELIVERED
    }

    // Getters and setters...
}
