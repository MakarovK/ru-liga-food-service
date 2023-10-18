package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.DAO.RestaurantDAO;
import ru.liga.entity.Restaurant;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private RestaurantDAO restaurantDAO;
    @Autowired
    public RestaurantController(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    @GetMapping("/{restaurant_id}")
    public Restaurant getRestaurantById(@PathVariable("restaurant_id") Long id) {
        return restaurantDAO.getRestaurantById(id);
    }
}
