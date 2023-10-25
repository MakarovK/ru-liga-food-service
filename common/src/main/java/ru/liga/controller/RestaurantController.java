package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RestaurantDTO;
import ru.liga.enums.KitchenStatus;
import ru.liga.service.RestaurantService;

@RestController
@RequestMapping("/feign-restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{restaurant_id}")
    public RestaurantDTO getRestaurantById(@PathVariable("restaurant_id") Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/{restaurant_id}/change-status")
    public String changeRestaurantStatus(@PathVariable("restaurant_id") Long id, @RequestBody KitchenStatus kitchenStatus) {
        restaurantService.changeRestaurantStatus(id, kitchenStatus);
        return "Статус заказа изменён";
    }
}
