package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.RestaurantDTO;
import ru.liga.dto.RestaurantMenuItemDTO;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<RestaurantMenuItemDTO> listEntityToDto(List<RestaurantMenuItem> restaurantMenuItems) {
        return restaurantMenuItems.stream()
                .map(RestaurantMenuItemConverter::entityToDto)
                .collect(Collectors.toList());
    }

    public static List<RestaurantMenuItem> listDtoToEntity(List<RestaurantMenuItemDTO> restaurantMenuItemDTOS) {
        return restaurantMenuItemDTOS.stream()
                .map(RestaurantMenuItemConverter::dtoToEntity)
                .collect(Collectors.toList());
    }
}