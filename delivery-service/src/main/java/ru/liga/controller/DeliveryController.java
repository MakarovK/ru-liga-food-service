package ru.liga.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.CourierDTO;
import ru.liga.service.CourierControllerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/couriers")

public class DeliveryController {
    @Autowired
    private CourierControllerService courierControllerService;

    @GetMapping("/{courier_id}")
    public CourierDTO getCourierById(@PathVariable("courier_id") Long courierId) {
        return courierControllerService.getCourierById(courierId);
    }

    @GetMapping("/all")
    public List<CourierDTO> getAllCouriers() {
        return courierControllerService.getAllCouriers();
    }

    @PutMapping("/{courier_id}/accept/{order_id}")
    public String acceptOrder(@PathVariable("courier_id") Long courier_id, @PathVariable("order_id") UUID order_id) {
        courierControllerService.acceptOrder(courier_id, order_id);
        return "Заказ принят";
    }
}
//, @PathVariable("order_id") Long order_id