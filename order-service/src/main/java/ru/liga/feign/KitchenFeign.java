package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RestaurantDTO;
import ru.liga.dto.RestaurantMenuItemDTO;

import java.util.List;

@FeignClient(name = "KitchenFeign", url = "http://localhost:8082")
public interface KitchenFeign {


    @GetMapping("/kitchen/restaurant/{id}")
    RestaurantDTO getRestaurantById(@PathVariable("id") Long id);

    @GetMapping("/kitchen/restaurant-menu-item/{id}")
    RestaurantMenuItemDTO getRestaurantMenuItemById(@PathVariable("id") Long id);

    @GetMapping("/kitchen/restaurants")
    List<RestaurantDTO> getRestaurants();

    @GetMapping("/kitchen/restaurant-menu-items/{id}/price")
    Integer getPriceByRestaurantMenuItemId(@PathVariable("id") Long id);

}
