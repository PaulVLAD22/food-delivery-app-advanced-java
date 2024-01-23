package com.example.vladfood.executor;

import com.example.vladfood.service.OrderService;
import com.example.vladfood.service.RestaurantService;
import com.example.vladfood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostStartupExecutor implements ApplicationListener<ContextRefreshedEvent> {
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Application Logic Starting...");
    }

}

