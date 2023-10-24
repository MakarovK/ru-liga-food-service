package ru.liga.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.liga.entity.Order;
import ru.liga.entity.RestaurantMenuItem;
@Data
@Accessors(chain = true)
public class OrderItemDTO {
    private long Id;
    private Order order_id;
    private RestaurantMenuItem restaurantMenuItem_id;
    private Integer price;
    private Integer quantity;
}
