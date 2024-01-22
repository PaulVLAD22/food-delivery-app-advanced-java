package com.example.vladfood.executor;

import com.example.vladfood.model.Order;
import com.example.vladfood.model.OrderItem;
import com.example.vladfood.model.Restaurant;
import com.example.vladfood.model.User;
import com.example.vladfood.service.OrderService;
import com.example.vladfood.service.RestaurantService;
import com.example.vladfood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PostStartupExecutor implements ApplicationListener<ContextRefreshedEvent> {
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Application Logic Starting...");
        User customer1 = getSampleCustomer();
        User customer2 = getSecondSampleCustomer();
        User deliveryPerson = getDeliveryPerson();
        Restaurant restaurant = getSampleRestaurant();

        //TODO :: implement
        var order = generateSampleOrder();
        orderService.createOrder(order);
        // check that updating status of order works (observer pattern)
        // make sure order got delivered
        // leave reviews from customers to restaurant - check that they have an order with status FINISHED before letting them!


    }

    List<OrderItem> getSampleOrderItems() {
        return List.of();
    }

    User getSampleCustomer() {
        return userService.findByUsername("customer1");
    }

    User getSecondSampleCustomer(){
        return userService.findByUsername("customer2");
    }

    User getDeliveryPerson() {
        return userService.findByUsername("delivery1");
    }

    Restaurant getSampleRestaurant() {
        return restaurantService.findRestaurantByName("Restaurant 1");
    }

    Order generateSampleOrder() {
        var orderItems = getSampleOrderItems();
        // return Order
        return new Order();
    }


}

