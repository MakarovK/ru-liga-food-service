package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.entity.Restaurant;
import ru.liga.feign.RestaurantFeign;

@RestController
@RequestMapping("/restaurants")
public class RestaurantFeignController {
    private RestaurantFeign restaurantFeign;

    @Autowired
    public RestaurantFeignController(RestaurantFeign restaurantFeign) {
        this.restaurantFeign = restaurantFeign;
    }

    @GetMapping("/{restaurant_id}")
    public Restaurant getRestaurantById(@PathVariable("restaurant_id") Long restaurant_id) {
        return restaurantFeign.getRestaurantById(restaurant_id);
    }
}
