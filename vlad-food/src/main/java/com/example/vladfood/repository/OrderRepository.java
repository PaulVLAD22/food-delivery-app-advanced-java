package com.example.vladfood.repository;

import com.example.vladfood.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long>{
}
