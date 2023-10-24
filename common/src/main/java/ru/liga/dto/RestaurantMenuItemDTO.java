package ru.liga.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.liga.entity.Restaurant;
@Data
@Accessors(chain = true)
public class RestaurantMenuItemDTO {
    private Long id;
    private Restaurant restaurant_id;
    private String name;
    private Integer price;
    private String image;
    private String description;
}
