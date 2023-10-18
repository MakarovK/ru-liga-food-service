package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.entity.Courier;
import ru.liga.DAO.CourierDAO;

@RestController
@RequestMapping("/couriers")
public class CourierController {
    private CourierDAO courierDAO;

    @Autowired
    public CourierController(CourierDAO courierDAO) {
        this.courierDAO = courierDAO;
    }
    @GetMapping("/{courier_id}")
    public Courier getCourierById(@PathVariable("courier_id") Long id) {
        return courierDAO.getCourierById(id);
    }
}
