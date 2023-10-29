package ru.liga.batisMapper;
import ru.liga.entity.OrderItem;
import ru.liga.entity.RestaurantMenuItem;

import java.util.List;
public interface CommonMapper {
    List<OrderItem> getOrderItems();
    void updatePrice(RestaurantMenuItem restaurantMenuItem);
}
