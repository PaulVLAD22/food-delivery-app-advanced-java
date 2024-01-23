package com.example.vladfood.repository;

import com.example.vladfood.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT o FROM Order o WHERE o.customer.username = ?1 AND o.status != 'DELIVERED'")
    Optional<Order> findActiveOrderByUsername(String customerUsername);
}
