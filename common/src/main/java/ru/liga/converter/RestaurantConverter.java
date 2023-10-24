package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.RestaurantDTO;
import ru.liga.entity.Restaurant;

@Component
public class RestaurantConverter {
    public RestaurantDTO entityToDto(Restaurant entity) {
        return new RestaurantDTO()
                .setId(entity.getId())
                .setAddress(entity.getAddress())
                .setKitchenStatus(entity.getKitchenStatus());
    }
    public Restaurant dtoToEntity(RestaurantDTO restaurant) {
        return new Restaurant()
                .setId(restaurant.getId())
                .setAddress(restaurant.getAddress())
                .setKitchenStatus(restaurant.getKitchenStatus());
    }
}
