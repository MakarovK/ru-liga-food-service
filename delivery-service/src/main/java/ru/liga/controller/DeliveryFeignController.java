package ru.liga.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.entity.Courier;
import ru.liga.feign.DeliveryFeign;

@RestController
@RequestMapping("/couriers")
@ComponentScan("ru.liga.feign")
public class DeliveryFeignController {
    private DeliveryFeign deliveryFeign;

    @Autowired
    public DeliveryFeignController(DeliveryFeign deliveryFeign) {
        this.deliveryFeign = deliveryFeign;
    }

    @GetMapping("/{courier_id}")
    public Courier getCourierById(@PathVariable("courier_id") Long courier_id) {
        return deliveryFeign.getCourierById(courier_id);
    }
}
