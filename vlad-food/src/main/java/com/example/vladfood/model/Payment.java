package com.example.vladfood.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    // Assuming PaymentStatus is an enum (PENDING, COMPLETED, REFUNDED)
    public enum PaymentStatus {
        PENDING, COMPLETED, REFUNDED
    }

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Getters and setters...
}
