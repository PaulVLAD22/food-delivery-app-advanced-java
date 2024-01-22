package com.example.vladfood.repository;

import com.example.vladfood.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
