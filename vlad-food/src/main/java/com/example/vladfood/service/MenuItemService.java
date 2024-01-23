package com.example.vladfood.service;

import com.example.vladfood.model.MenuItem;
import com.example.vladfood.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItem findByRestaurantNameAndMenuItemName(String restaurantName, String menuItemName) {
        var menuItemOptional = menuItemRepository.findByRestaurantNameAndName(restaurantName, menuItemName);
        if (menuItemOptional.isEmpty()) {
            throw new RuntimeException("Menu item not found");
        }
        return menuItemOptional.get();
    }
}
