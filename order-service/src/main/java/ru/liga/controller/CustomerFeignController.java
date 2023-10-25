package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.feign.CoreFeign;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerFeignController {
    private CoreFeign coreFeign;

    @Autowired
    public CustomerFeignController(CoreFeign coreFeign) {
        this.coreFeign = coreFeign;
    }

    @GetMapping("/{customer_id}/history")
    public List<OrderDTO> getCustomerHistoryById(@PathVariable("customer_id") Long id) {
        return coreFeign.getCustomerHistoryById(id);
    }
    @PostMapping("/{customer_id}/create")
    public String createOrder(@PathVariable("customer_id") Long id, @RequestBody OrderDTO orderDTO) {
        coreFeign.createOrder(id, orderDTO);
        return "Заказ создан";
    }

}
