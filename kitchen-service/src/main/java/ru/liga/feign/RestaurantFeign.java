package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.liga.entity.Restaurant;

/**
 * Интерфейс Feign для взаимодействия с сервисом ресторанов.
 */
@FeignClient(name = "RestaurantFeign", url = "http://localhost:8087")
public interface RestaurantFeign {
    /**
     * Получение ресторана по его идентификатору.
     *
     * @param id Идентификатор ресторана.
     * @return Объект Restaurant, представляющий ресторан.
     */
    @GetMapping("/feign-restaurants/{restaurant_id}")
    Restaurant getRestaurantById(@PathVariable("restaurant_id") Long id);
}
