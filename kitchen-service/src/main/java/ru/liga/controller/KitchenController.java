package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.service.KitchenFeignService;

import java.util.List;

/**
 * Контроллер для взаимодействия с сервисом кухни.
 */
@RestController
@RequestMapping("/kitchen")
public class KitchenController {
    @Autowired
    private KitchenFeignService kitchenFeignService;

    /**
     * Получение всех текущих заказов.
     *
     * @return Список объектов OrderDTO, представляющих текущие заказы.
     */
    @GetMapping("/{restaurant_id}/orders")
    public List<OrderDTO> getAllCurrentOrders(@PathVariable("restaurant_id") Long restaurant_id) {
        return kitchenFeignService.getAllCurrentOrders(restaurant_id);
    }

    @PutMapping("/{restaurant_id}/accept/{order_id}")
    public String acceptOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id) {
        kitchenFeignService.acceptOrder(id, restaurant_id);
        //Поиск курьера send in Queue и как только он отправляет мессаж о принятии, мы меняем статус заказа на DELIVERY
        //Синхронное и асинхронное взаимодействие
        return "Заказ принят";
    }

    @PutMapping("/{restaurant_id}/reject/{order_id}")
    public String rejectOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id) {
        kitchenFeignService.rejectOrder(id, restaurant_id);
        return "Заказ отклонён";
    }

    @PutMapping("/{restaurant_id}/complete/{order_id}")
    public String completeOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id) {
        kitchenFeignService.completeOrder(id, restaurant_id);
        return "Заказ приготовлен";
    }
}
