package com.example.vladfood.intializer;


import com.example.vladfood.model.MenuItem;
import com.example.vladfood.model.Restaurant;
import com.example.vladfood.model.User;
import com.example.vladfood.repository.MenuItemRepository;
import com.example.vladfood.repository.RestaurantRepository;
import com.example.vladfood.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;

    public DataInitializer(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository,
                           UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var restaurant1 = Restaurant.builder()
                .name("restaurant1")
                .description("Restaurant 1 description")
                .address("Restaurant 1 address")
                .build();
        restaurantRepository.save(
                restaurant1
        );

        menuItemRepository.save(
                MenuItem.builder()
                        .name("Burger")
                        .description("Burger with cheese")
                        .price(5.0)
                        .restaurant(restaurant1)
                        .build()
        );
        menuItemRepository.save(
                MenuItem.builder()
                        .name("Pasta")
                        .description("Pasta with cheese")
                        .price(8.0)
                        .restaurant(restaurant1)
                        .build()
        );
        menuItemRepository.save(
                MenuItem.builder()
                        .name("Pizza")
                        .description("Pizza with cheese")
                        .price(10.0)
                        .restaurant(restaurant1)
                        .build()
        );
        var customer1 = User.createUser(User.UserType.CUSTOMER,
                "customer1", "pass1", "Vlad Paul", "email1@email.com", "address1");
        var customer2 = User.createUser(
                User.UserType.CUSTOMER,
                "customer2", "pass1", "Nume Prenume", "email2@email.com", "address2"
        );
        var delivery1 = User.createUser(User.UserType.DELIVERY,
                "delivery1", "pass1", "Iran Bahur", "email3@email.com", "address3");

        var delivery2 = User.createUser(User.UserType.DELIVERY,
                "delivery2", "pass2", "Iran Bahur2", "email4@email.com", "address4");
        userRepository.save(customer1);
        userRepository.save(customer2);
        userRepository.save(delivery1);
        userRepository.save(delivery2);
    }
}

