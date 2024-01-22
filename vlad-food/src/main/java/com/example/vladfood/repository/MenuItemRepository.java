package com.example.vladfood.repository;

import com.example.vladfood.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
