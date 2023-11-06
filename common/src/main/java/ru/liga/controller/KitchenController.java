package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.service.KitchenService;

import java.util.List;

@RestController
@RequestMapping("/feign-kitchen")
@Tag(name = "Контроллер для кухни")
public class KitchenController {

    @Autowired
    @Schema(name = "Объект класса сервиса")
    private KitchenService kitchenService;

    @GetMapping
    @Operation(description = "Выводит строку Сервис кухни")
    public String def() {
        return "Сервис кухни";
    }

    @GetMapping("/{restaurant_id}/preparing")
    @Operation(description = "Выводит все заказа со статусом Preparing")
    public List<OrderDTO> getAllPreparingOrders(@PathVariable("restaurant_id") Long restaurant_id) {
        return kitchenService.getAllPreparingOrders(restaurant_id);
    }
    @GetMapping("/{restaurant_id}/created")

    @Operation(description = "Выводит все заказа со статусом Created")
    public List<OrderDTO> getAllCreatedOrders(@PathVariable("restaurant_id") Long restaurant_id) {
        return kitchenService.getALLCreatedOrders(restaurant_id);
    }

    @PutMapping("{restaurant_id}/accept/{order_id}")
    @Operation(description = "Принимает заказ по ID")
    public void acceptOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id) {
        kitchenService.acceptOrder(id, restaurant_id);
    }

    @PutMapping("{restaurant_id}/reject/{order_id}")

    @Operation(description = "Отклоняет заказ по ID")
    public void rejectOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id) {
        kitchenService.rejectOrder(id, restaurant_id);
    }

//    @PutMapping("{restaurant_id}/complete/{order_id}")
//    public void completeOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id) {
//        kitchenService.completeOrder(id, restaurant_id);
//    }
}
