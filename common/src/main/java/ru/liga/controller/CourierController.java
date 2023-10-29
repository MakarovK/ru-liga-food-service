package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.CourierDTO;
import ru.liga.service.CourierService;

import java.util.List;

@RestController
@RequestMapping("/feign-couriers")
public class CourierController {
    @Autowired
    private CourierService courierService;

    @GetMapping("/couriers")
    public List<CourierDTO> getAllCouriers() {
        return courierService.getAllCouriers();
    }
}
