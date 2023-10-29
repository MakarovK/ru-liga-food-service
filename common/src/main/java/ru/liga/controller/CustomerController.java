package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/feign-customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customer_id}/history")
    public List<OrderDTO> getCustomerHistoryById(@PathVariable("customer_id") Long id) {
        return customerService.getOrdersByCustomerId(id);
    }

    @PostMapping("/{customer_id}/create")
    public String createOrder(@PathVariable("customer_id") Long id, @RequestBody OrderDTO orderDTO) throws JsonProcessingException {
        return "Заказ создан под id " + customerService.createOrder(id, orderDTO);
    }
}
