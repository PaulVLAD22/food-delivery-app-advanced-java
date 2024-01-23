package com.example.vladfood.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String description;

    private Double rating;

    @Builder.Default
    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menuItems = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews = new ArrayList<>();

    public void sendOrderStatusUpdate(Order.OrderStatus newStatus) {
        if (newStatus == Order.OrderStatus.IN_PROGRESS) {
            System.out.println("Restaurant starts preparing the food");
        }
    }
    // Getters and setters...
}
