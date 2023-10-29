package ru.liga.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.enums.KitchenStatus;

import java.util.List;

@Data
@Accessors(chain = true)
public class RestaurantDTO {
    private Long id;

    private String address;

    private List<RestaurantMenuItem> restaurantMenuItems;

    private KitchenStatus kitchenStatus;

    private String coordinates;
}
