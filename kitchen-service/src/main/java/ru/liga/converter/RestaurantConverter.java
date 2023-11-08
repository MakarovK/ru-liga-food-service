package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.RestaurantDTO;
import ru.liga.entity.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantConverter {
    public static RestaurantDTO entityToDto(Restaurant entity) {
        return new RestaurantDTO()
                .setId(entity.getId())
                .setAddress(entity.getAddress())
                .setRestaurantMenuItems(entity.getRestaurantMenuItems()
                        .stream()
                        .map(RestaurantMenuItemConverter::entityToDto)
                        .collect(Collectors.toList()))
                .setKitchenStatus(entity.getKitchenStatus())
                .setCoordinates(entity.getCoordinates());
    }
    public static Restaurant dtoToEntity(RestaurantDTO restaurant) {
        return new Restaurant()
                .setId(restaurant.getId())
                .setAddress(restaurant.getAddress())
                .setRestaurantMenuItems(restaurant.getRestaurantMenuItems()
                        .stream()
                        .map(RestaurantMenuItemConverter::dtoToEntity)
                        .collect(Collectors.toList()))
                .setKitchenStatus(restaurant.getKitchenStatus())
                .setCoordinates(restaurant.getCoordinates());
    }

    public static List<RestaurantDTO> listEntityToDto(List<Restaurant> restaurants) {
        List<RestaurantDTO> restaurantDTOS = restaurants.stream()
                .map(RestaurantConverter::entityToDto)
                .collect(Collectors.toList());
        return restaurantDTOS;
    }

    public static List<Restaurant> listDtoToEntity(List<RestaurantDTO> restaurantsDTO) {
        List<Restaurant> restaurants = restaurantsDTO.stream()
                .map(RestaurantConverter::dtoToEntity)
                .collect(Collectors.toList());
        return restaurants;
    }
}