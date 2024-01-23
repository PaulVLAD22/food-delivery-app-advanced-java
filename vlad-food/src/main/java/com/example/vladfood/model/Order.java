package com.example.vladfood.model;

import com.example.vladfood.dto.OrderDto;
import com.example.vladfood.service.MenuItemService;
import com.example.vladfood.service.RestaurantService;
import com.example.vladfood.service.UserService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
@Getter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private LocalDateTime orderTime;

    @Setter
    private LocalDateTime deliveryTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setOrderStatus(OrderStatus newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    public enum OrderStatus {
        NEW, IN_PROGRESS, DELIVERED, CANCELED
    }

    @Setter
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Setter
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Setter
    @ManyToOne
    @JoinColumn(name = "deliverer_id")
    private User deliverer;


    // create order factory
    public static Order createOrder(User customer, User deliverer, Restaurant restaurant, Set<OrderItem> orderItems) {
        Order order = new Order();
        System.out.println(customer);
        order.setCustomer(customer);
        order.setDeliverer(deliverer);
        order.setRestaurant(restaurant);
        order.setOrderTime(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.NEW);
        order.setOrderItems(orderItems);
        order.setDeliveryTime(LocalDateTime.now().plusMinutes(30));
        return order;
    }

    // observer
    public void notifyObservers() {
        // Notify the customer and deliverer (observers) based on the order status
        if (status == OrderStatus.IN_PROGRESS) {
            restaurant.sendOrderStatusUpdate(this.getStatus());
        }
        if (status == OrderStatus.IN_PROGRESS || status == OrderStatus.CANCELED || status == OrderStatus.DELIVERED) {

            // notify the restaurant the order is in progress

            // Notify the customer
            customer.sendOrderStatusUpdate(this.getStatus());

            if (deliverer != null) {
                // Notify the deliverer
                deliverer.sendOrderStatusUpdate(this.getStatus());
            }
        }
    }

    public static Order dtoToModel(OrderDto createOrderDto, UserService userService, RestaurantService restaurantService,
                                   MenuItemService menuItemService) {
        var customer = userService.findByUsername(createOrderDto.getCustomerUsername());
        var deliverer = userService.getFreeDeliverer();
        var restaurant = restaurantService.findRestaurantByName(createOrderDto.getRestaurantName());
        Set<OrderItem> orderItems = createOrderDto.getOrderItems().stream().map(
                orderItemDto -> OrderItem.dtoToModel(orderItemDto,
                        menuItemService.findByRestaurantNameAndMenuItemName(createOrderDto.getRestaurantName(), orderItemDto.getMenuItemName()))
        ).collect(Collectors.toSet());
        return createOrder(customer, deliverer, restaurant, orderItems);

    }

}
