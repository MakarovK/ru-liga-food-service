package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.RestaurantMenuItemDTO;
import ru.liga.entity.RestaurantMenuItem;

@Component
public class RestaurantMenuItemConverter {
    public static RestaurantMenuItemDTO entityToDto(RestaurantMenuItem entity) {
        return new RestaurantMenuItemDTO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPrice(entity.getPrice())
                .setImage(entity.getImage())
                .setDescription(entity.getDescription());
    }
    public static RestaurantMenuItem dtoToEntity(RestaurantMenuItemDTO restaurantMenuItemDTO) {
        return new RestaurantMenuItem()
                .setId(restaurantMenuItemDTO.getId())
                .setName(restaurantMenuItemDTO.getName())
                .setPrice(restaurantMenuItemDTO.getPrice())
                .setImage(restaurantMenuItemDTO.getImage())
                .setDescription(restaurantMenuItemDTO.getDescription());
    }
}
