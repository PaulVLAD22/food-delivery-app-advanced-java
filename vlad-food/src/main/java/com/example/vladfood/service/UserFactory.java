package com.example.vladfood.service;

import com.example.vladfood.model.User;
import com.example.vladfood.model.User.UserType;

public class UserFactory {

    public static User createUser(UserType userType) {
        User user = new User();
        user.setUserType(userType);
        return user;
    }
}

