package ru.liga.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.liga.model.DeliveryDTO;

@Tag(name = "Api для доставки")
@RestController
@RequestMapping

public class DeliveryController {
    @Operation (summary = "Получить детали доставки по ID")
    @GetMapping("/{id}")
    public DeliveryDTO getDeliveryById(@PathVariable("id") Long id,
                                       @PathVariable("restaurantAddress") String restaurantAddress,
                                       @PathVariable("restaurantDistance") Double restaurantDistance,
                                       @PathVariable("customerAddress") String customerAddress,
                                       @PathVariable("distanceCustomer") Double distanceCustomer,
                                       @PathVariable("payment") Double payment) {
        return new DeliveryDTO()
                .setId(id)
                .setRestaurantAddress(restaurantAddress)
                .setRestaurantDistance(restaurantDistance)
                .setCustomerAddress(customerAddress)
                .setDistanceCustomer(distanceCustomer)
                .setPayment(payment);
    }

    @Operation (summary = "Обновить доставку")
    @PutMapping("/update")
    public String updateDTO(@RequestBody DeliveryDTO deliveryDTO) {

        return "Доставка изменена";
    }

    @Operation (summary = "Обновить оплату по ID доставки")
    @PatchMapping("/{id}")
    public String updatePaymentDTO(@PathVariable("id") Long id,
                                   @PathVariable Double payment,
                                   DeliveryDTO deliveryDTO) {
        return "Оплата заказа изменена";
    }

    @Operation (summary = "Создать доставку")
    @PostMapping("/create")
    public String create(@RequestBody DeliveryDTO deliveryDTO) {
        return "Новая доставка создана";
    }

    @Operation (summary = "Удалить доставку по ID")
    @DeleteMapping ("/delete/{id}")
    public String deleteDeliveryById(@PathVariable("id") Long id) {
        return "Доставка под номером " + id + " удалена";
    }

}
