package com.example.vladfood.repository;

import com.example.vladfood.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.userType = 'DELIVERY' AND u.id NOT IN (SELECT o.deliverer.id FROM Order o WHERE o.status = 'DELIVERED') ")
    List<User> getFreeDeliverer(Pageable pageable);

}
