package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.entity.Customer;
import ru.liga.feign.OrderFeign;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerFeignController {
    private OrderFeign orderFeign;

    @Autowired
    public CustomerFeignController(OrderFeign orderFeign) {
        this.orderFeign = orderFeign;
    }

    @GetMapping("/{customer_id}")
    public Customer getCustomerById(@PathVariable("customer_id") Long customer_id) {
        return orderFeign.getCustomerById(customer_id);
    }

    @GetMapping("/all")
    public List<Customer> getCustomers() {
        return orderFeign.getCustomers();
    }
}
