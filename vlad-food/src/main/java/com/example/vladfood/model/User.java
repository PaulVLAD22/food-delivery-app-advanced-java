package com.example.vladfood.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    // Assuming UserType is an enum (CUSTOMER, RESTAURANT_OWNER, DELIVERY)
    public enum UserType {
        CUSTOMER, DELIVERY
    }

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    public static User createUser(UserType userType, String username, String password, String fullName, String email, String address) {
        User user = new User ();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setAddress(address);
        user.setUserType(userType);
        return user;
    }

    // consumer
    public void sendOrderStatusUpdate(Order.OrderStatus newStatus) {
        if (userType == UserType.CUSTOMER) {
            System.out.println("Customer received order status update: " + newStatus);
        } else if (userType == UserType.DELIVERY) {
            System.out.println("Delivery person received order status update: " + newStatus);
        }
    }

    // Getters and setters...
}