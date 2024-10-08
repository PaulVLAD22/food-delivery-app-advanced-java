package com.example.vladfood.repository;

import com.example.vladfood.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByRestaurantNameAndName(String restaurantName, String name);
}
