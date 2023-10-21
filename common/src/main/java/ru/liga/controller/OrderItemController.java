package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.batisMapper.CommonMapper;
import ru.liga.entity.OrderItem;

import java.util.List;


@RestController("/feign-order-item")
public class OrderItemController {
    /*
    private CommonMapper commonMapper;

    @Autowired
    public OrderItemController(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    @GetMapping("/all")
    public List<OrderItem> getOrderAllItems() {
        return commonMapper.getOrderItems();
    }*/
}
