package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.RestaurantMenuItemConverter;
import ru.liga.dto.RestaurantMenuItemDTO;
import ru.liga.repository.RestaurantMenuItemRepository;

@Service
public class RestaurantMenuItemService {
    @Autowired
    private RestaurantMenuItemRepository restaurantMenuItemRepository;

    public RestaurantMenuItemDTO getRestaurantMenuItemById(Long id) {
        return RestaurantMenuItemConverter.entityToDto(restaurantMenuItemRepository.findById(id).orElse(null));
    }
    public void changeRestaurantItemPrice(Long id, Integer price) {
        restaurantMenuItemRepository.save(RestaurantMenuItemConverter.dtoToEntity(getRestaurantMenuItemById(id).setPrice(price)));
    }
}
