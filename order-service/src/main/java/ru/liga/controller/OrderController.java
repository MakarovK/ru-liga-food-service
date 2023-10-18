package ru.liga.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.entity.Customer;
import ru.liga.service.CustomerDAO;

@Tag(name = "Api для заказов")
@RestController
@RequestMapping("/customers")
public class OrderController {
    private CustomerDAO customerDAO;
    @Autowired
    public OrderController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping
    public String getHello() {
        return "HelloWorld";
    }
    @GetMapping("/{customer_id}")
    public Customer getCustomerById(@PathVariable("customer_id") Long id) {
        return customerDAO.getCustomerById(id);
    }
}
