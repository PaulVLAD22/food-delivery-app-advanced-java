package com.example.vladfood.service;

import com.example.vladfood.model.User;
import com.example.vladfood.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void userRatesRestaurant(User user, int rating){
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
}
