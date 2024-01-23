package com.example.vladfood.service;

import com.example.vladfood.model.User;
import com.example.vladfood.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void userRatesRestaurant(User user, int rating) {
        // check user has ordered from restaurant

        // insert review in db
        System.out.println("User rates restaurant: " + user);
    }

    public User findByUsername(String username) {
        var userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return userOptional.get();
    }

    public User getFreeDeliverer() {
        Optional<User> freeDeliveryPerson = userRepository.getFreeDeliverer(PageRequest.of(0, 1))
                .stream().findFirst();
        if (freeDeliveryPerson.isEmpty()) {
            throw new RuntimeException("No free delivery person found");
        }
        return freeDeliveryPerson.get();

    }

}
