package ru.liga.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.entity.Customer;
import ru.liga.DAO.CustomerDAO;

import java.util.List;

@Tag(name = "Api для заказов")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerDAO customerDAO;
    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("/{customer_id}")
    public Customer getCustomerById(@PathVariable("customer_id") Long id) {
        return customerDAO.getCustomerById(id);
    }

    @GetMapping("/all")
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }
}
