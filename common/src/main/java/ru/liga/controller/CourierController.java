package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.CourierDTO;
import ru.liga.service.CourierService;

import java.util.List;

@RestController
@RequestMapping("/feign-couriers")
@Tag(name = "Контроллеры для курьеров")
public class CourierController {
    @Autowired
    private CourierService courierService;

    @GetMapping
    @Operation(description = "Возвращает строку Сервис Доставки")
    public String def() {
        return "Сервис доставки";
    }

    @GetMapping("/couriers")
    @Operation(description = "Получить список всех курьеров")
    public List<CourierDTO> getAllCouriers() {
        return courierService.getAllCouriers();
    }
}
