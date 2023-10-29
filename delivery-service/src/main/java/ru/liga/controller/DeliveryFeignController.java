package ru.liga.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.liga.entity.Courier;
import ru.liga.feign.DeliveryFeign;
import ru.liga.service.CourierFeignService;
import ru.liga.service.DeliveryOrderListener;
import ru.liga.service.RabbitMQDeliveryServiceImpl;

@RestController
@RequestMapping("/couriers")

public class DeliveryFeignController {
    private DeliveryFeign deliveryFeign;
    @Autowired
    private RabbitMQDeliveryServiceImpl rabbitMQDeliveryService;
    @Autowired
    private CourierFeignService courierFeignService;
    @Autowired
    private DeliveryOrderListener deliveryOrderListener;
    @Autowired
    public DeliveryFeignController(DeliveryFeign deliveryFeign) {
        this.deliveryFeign = deliveryFeign;
    }

    @GetMapping("/{courier_id}")
    public Courier getCourierById(@PathVariable("courier_id") Long courier_id) {
        return deliveryFeign.getCourierById(courier_id);
    }

    @GetMapping("/{courier_id}/accept/{order_id}")
    public String acceptOrder(@PathVariable("courier_id") Long courier_id, @PathVariable("order_id") Long order_id) {
        courierFeignService.acceptOrder(courier_id, order_id);
        return "Заказ принят";
    }
}
//, @PathVariable("order_id") Long order_id