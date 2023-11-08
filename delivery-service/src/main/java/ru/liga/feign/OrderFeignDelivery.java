package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "OrderFeignDelivery", url = "http://localhost:8084")
public interface OrderFeignDelivery {
    @PutMapping("/customer/{uuid}/courier-deny")
    String courierDenyOrder(@PathVariable("uuid") UUID uuid);

    @PutMapping("/customer/{uuid}/courier-accept")
    String courierAcceptOrder(@PathVariable("uuid") UUID uuid);

    @PutMapping("/customer/{uuid}/courier-complete")
    String courierCompleteOrder(@PathVariable("uuid") UUID uuid);
}