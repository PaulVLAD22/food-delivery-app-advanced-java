package com.example.vladfood.service;

import com.example.vladfood.model.Restaurant;
import com.example.vladfood.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    public Restaurant findRestaurantByName(String name) {
        var resturantOptional = restaurantRepository.findByName(name);
        if (resturantOptional.isEmpty()) {
            throw new RuntimeException("Restaurant not found");
        }
        return resturantOptional.get();
    }
}
