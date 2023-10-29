package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.service.KitchenService;

import java.util.List;

@RestController
@RequestMapping("/feign-kitchen")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @GetMapping("/{restaurant_id}/preparing")
    public List<OrderDTO> getAllPreparingOrders(@PathVariable("restaurant_id") Long restaurant_id) {
        return kitchenService.getAllPreparingOrders(restaurant_id);
    }
    @GetMapping("/{restaurant_id}/created")
    public List<OrderDTO> getAllCreatedOrders(@PathVariable("restaurant_id") Long restaurant_id) {
        return kitchenService.getALLCreatedOrders(restaurant_id);
    }

    @PutMapping("{restaurant_id}/accept/{order_id}")
    public void acceptOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id) {
        kitchenService.acceptOrder(id, restaurant_id);
    }

    @PutMapping("{restaurant_id}/reject/{order_id}")
    public void rejectOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id) {
        kitchenService.rejectOrder(id, restaurant_id);
    }

//    @PutMapping("{restaurant_id}/complete/{order_id}")
//    public void completeOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id) {
//        kitchenService.completeOrder(id, restaurant_id);
//    }
}
