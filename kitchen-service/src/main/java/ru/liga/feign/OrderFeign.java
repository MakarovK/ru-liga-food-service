package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "OrderFeign", url = "http://localhost:8084")
public interface OrderFeign {
    @PutMapping("/customer/{uuid}/deny")
    String denyOrder(@PathVariable("uuid") UUID uuid);

    @PutMapping("/customer/{uuid}/accept")
    String acceptOrder(@PathVariable("uuid") UUID uuid);

    @PutMapping("/customer/{uuid}/complete")
    String completeOrder(@PathVariable("uuid") UUID uuid);

}
