package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.liga.entity.Courier;


@FeignClient(name = "DeliveryFeign", url = "http://localhost:8087")
public interface DeliveryFeign {

    //Courier API
    @GetMapping("/feign-couriers/{courier_id}")
    Courier getCourierById(@PathVariable("courier_id") Long id);
}
