package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.RestaurantMenuItemDTO;
import ru.liga.entity.RestaurantMenuItem;

@Component
public class RestaurantMenuItemConverter {
    public RestaurantMenuItemDTO entityToDto(RestaurantMenuItem entity) {
        return new RestaurantMenuItemDTO()
                .setId(entity.getId())
                .setRestaurant_id(entity.getRestaurant_id())
                .setName(entity.getName())
                .setPrice(entity.getPrice())
                .setImage(entity.getImage())
                .setDescription(entity.getDescription());
    }
    public RestaurantMenuItem dtoToEntity(RestaurantMenuItemDTO restaurantMenuItemDTO) {
        return new RestaurantMenuItem()
                .setId(restaurantMenuItemDTO.getId())
                .setRestaurant_id(restaurantMenuItemDTO.getRestaurant_id())
                .setName(restaurantMenuItemDTO.getName())
                .setPrice(restaurantMenuItemDTO.getPrice())
                .setImage(restaurantMenuItemDTO.getImage())
                .setDescription(restaurantMenuItemDTO.getDescription());
    }
}
