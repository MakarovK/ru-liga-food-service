package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.RestaurantConverter;
import ru.liga.dto.RestaurantDTO;
import ru.liga.enums.KitchenStatus;
import ru.liga.repository.RestaurantRepository;

@Service
public class RestaurantService {
    @Autowired
    public RestaurantRepository restaurantRepository;

    public RestaurantDTO getRestaurantById(Long id) {
        return RestaurantConverter.entityToDto(restaurantRepository.findById(id).orElse(null));
    }

    public void changeRestaurantStatus(Long id, KitchenStatus kitchenStatus) {
        restaurantRepository.save(RestaurantConverter.dtoToEntity(getRestaurantById(id).setKitchenStatus(kitchenStatus)));
    }
}
