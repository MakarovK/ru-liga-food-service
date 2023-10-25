package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.RestaurantMenuItemDTO;
import ru.liga.service.RestaurantMenuItemService;

@RestController
@RequestMapping("/feign-restaurant-menu")
public class RestaurantMenuItemController {
    @Autowired
    private RestaurantMenuItemService restaurantMenuItemService;

    @GetMapping("/{restaurant_menu_item_id}")
    private RestaurantMenuItemDTO getRestaurantById(@PathVariable("restaurant_menu_item_id") Long id) {
        return restaurantMenuItemService.getRestaurantMenuItemById(id);
    }

    @PutMapping("/{restaurant_menu_item_id}/change-price")
    private String changeRestaurantItemPrice(@PathVariable("restaurant_menu_item_id") Long id, @RequestBody Integer price) {
        restaurantMenuItemService.changeRestaurantItemPrice(id,price);
        return "Цена заказа изменена";
    }
}
