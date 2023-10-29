package ru.liga.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.entity.OrderItem;
import ru.liga.feign.OrderFeign;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemFeignController {
    private OrderFeign orderFeign;
    /*

    public OrderItemFeignController(OrderFeign orderFeign) {
        this.orderFeign = orderFeign;
    }
    @GetMapping("/all")
    public List<OrderItem> getOrderAllItems() {
        return orderFeign.getOrderAllItems();
    }*/
}
