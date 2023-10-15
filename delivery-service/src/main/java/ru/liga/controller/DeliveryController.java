package ru.liga.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Api для доставки")
@RestController
//@RequestMapping("/deliveries")
public class DeliveryController {
    /*
    @GetMapping("/deliveries")
    @Operation (summary = "Получить детали доставки по статусу")
    public List<OrderDTO> getDeliveryById(@RequestParam("status") String status) {
        return OrdersDTO.getOrdersDTO()
                .getOrderDTOList().stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList());
    }

*/
}
