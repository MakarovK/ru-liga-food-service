package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.liga.entity.Restaurant;

@FeignClient(name = "RestaurantFeign", url = "http://localhost:8087")
public interface RestaurantFeign {
    @GetMapping("/feign-restaurants/{restaurant_id}")
    Restaurant getRestaurantById(@PathVariable("restaurant_id") Long id);
}
