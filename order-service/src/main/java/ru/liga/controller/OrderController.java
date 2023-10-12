package ru.liga.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;

@Tag(name = "Api для заказов")
@RestController
@RequestMapping
public class OrderController {
    @Operation(summary = "Получить детали заказа по ID")
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable("id") Long id,
                                 @PathVariable("restaurantName") String restaurantName,
                                 @PathVariable("timestamp") Integer timestamp,
                                 @PathVariable("price") Double price,
                                 @PathVariable("quantity") Integer quantity,
                                 @PathVariable("description") String description,
                                 @PathVariable("imagePath") String imagePath) {
        return new OrderDTO()
                .setId(id)
                .setRestaurantName(restaurantName)
                .setTimestamp(timestamp)
                .setPrice(price)
                .setQuantity(quantity)
                .setDescription(description)
                .setImagePath(imagePath);
    }

    @Operation(summary = "Обновить данные заказа")
    @PutMapping("/update")
    public String updateDTO(@RequestBody OrderDTO orderDTO) {

        return "Заказ изменён";
    }

    @Operation(summary = "Создать заказ")
    @PostMapping("/create")
    private String create(@PathVariable OrderDTO orderDTO) {

        return "Новый заказ создан";
    }

    @Operation(summary = "Удалить доствку по ID")
    @DeleteMapping ("/delete/{id}")
    public String deleteOrderById(@PathVariable("id") Long id) {

        return "Заказ под номером " + id + " удалён";
    }
}

