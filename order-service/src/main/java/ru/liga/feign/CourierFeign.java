package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.dto.RestaurantMenuItemDTO;

import java.util.List;

@FeignClient(name = "CourierFeign", url = "http://localhost:8081")
public interface CourierFeign {

    @GetMapping("/couriers/{courier_id}")
    CourierDTO getCourierById(@PathVariable("courier_id") Long courierId);

    @GetMapping("/couriers/all")
    List<CourierDTO> getAllCouriers();
}
