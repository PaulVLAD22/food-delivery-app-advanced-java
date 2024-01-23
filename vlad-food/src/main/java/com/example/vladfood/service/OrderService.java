package com.example.vladfood.service;

import com.example.vladfood.dto.OrderDto;
import com.example.vladfood.model.Order;
import com.example.vladfood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;

    public void createOrder(OrderDto orderDto) {
        checkUserAlreadyHasActiveOrder(orderDto.getCustomerUsername());
        var order = Order.dtoToModel(orderDto, userService, restaurantService, menuItemService);
        orderRepository.save(order);
        paymentService.processPayment(order);
    }

    private void checkUserAlreadyHasActiveOrder(String customerUsername) {
        if (orderRepository.findActiveOrderByUsername(customerUsername).isPresent()) {
            throw new RuntimeException("User has an active order");
        }
    }

    public void updateOrder(long orderId, Order.OrderStatus orderStatus) {
        var oder = orderRepository.findById(orderId);
        if (oder.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        oder.get().setOrderStatus(orderStatus);
        orderRepository.save(oder.get());
    }
}
