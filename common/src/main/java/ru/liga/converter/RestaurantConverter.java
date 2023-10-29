package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.RestaurantDTO;
import ru.liga.entity.Restaurant;

@Component
public class RestaurantConverter {
    public static RestaurantDTO entityToDto(Restaurant entity) {
        return new RestaurantDTO()
                .setId(entity.getId())
                .setAddress(entity.getAddress())
                .setRestaurantMenuItems(entity.getRestaurantMenuItems())
                .setKitchenStatus(entity.getKitchenStatus())
                .setCoordinates(entity.getCoordinates());
    }
    public static Restaurant dtoToEntity(RestaurantDTO restaurant) {
        return new Restaurant()
                .setId(restaurant.getId())
                .setAddress(restaurant.getAddress())
                .setRestaurantMenuItems(restaurant.getRestaurantMenuItems())
                .setKitchenStatus(restaurant.getKitchenStatus())
                .setCoordinates(restaurant.getCoordinates());
    }
}
