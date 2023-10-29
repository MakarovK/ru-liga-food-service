package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.DAO.CustomerDAO;
import ru.liga.entity.Customer;

import java.util.List;

@RestController
@RequestMapping("/feign-customers")
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
