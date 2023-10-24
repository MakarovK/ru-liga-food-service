package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.converter.OrderConverter;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/feign-customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        return customerService.getOrderById(id);
    }
    @GetMapping("/{customer_id}/history")
    public List<OrderDTO> getCustomerById(@PathVariable("customer_id") Long id) {
        return customerService.getOrdersByCustomerId(id);
    }

    @PostMapping("/{customer_id}/create")
    public String createOrder(@PathVariable("customer_id") Long id, @RequestBody OrderDTO orderDTO) {
        customerService.createOrder(id, orderDTO);
        return "Заказ создан";
    }
}
