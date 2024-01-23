package com.example.vladfood.model;

import com.example.vladfood.dto.OrderItemDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Setter
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public static OrderItem dtoToModel(OrderItemDto orderItemDto, MenuItem menuItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setMenuItem(menuItem);
        return orderItem;
    }

}