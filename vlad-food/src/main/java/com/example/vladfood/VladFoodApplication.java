package com.example.vladfood;

import com.example.vladfood.model.MenuItem;
import com.example.vladfood.model.Restaurant;
import com.example.vladfood.repository.MenuItemRepository;
import com.example.vladfood.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class VladFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(VladFoodApplication.class, args);
    }

    // insert some menu items on app startup
    @Bean
    CommandLineRunner initalizeData(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        return args -> {

        };
    }

    @Bean
    ApplicationListener<ContextRefreshedEvent> executeAfterStartup(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        return event -> {

        };
    }

}
