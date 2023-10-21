package ru.liga.entity;

import lombok.Data;

import javax.persistence.*;

@Data

public class OrderItem {
    private long id;

    private Order order_id;

    private RestaurantMenuItem restaurantMenuItem_id;

    private Integer price;

    private Integer quantity;
}
