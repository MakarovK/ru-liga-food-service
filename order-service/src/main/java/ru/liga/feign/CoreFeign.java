package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;

import java.util.List;

@FeignClient(name = "CoreFeign", url = "http://localhost:8087")
public interface CoreFeign {

    //Courier API

    @GetMapping("/feign-customers/{customer_id}/history")
    public List<OrderDTO> getCustomerHistoryById(@PathVariable("customer_id") Long id);

    @PostMapping("/feign-customers/{customer_id}/create")
    public String createOrder(@PathVariable("customer_id") Long id, @RequestBody OrderDTO orderDTO);


}
