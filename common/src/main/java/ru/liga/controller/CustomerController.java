package ru.liga.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/feign-customers")
@Tag(name = "Контроллеры для клиента")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    @Operation(description = "Выводит строку сервис клиента")
    public String def() {
        return "Сервис клиента";
    }

    @GetMapping("/{customer_id}/history")
    @Operation(description = "Выводит историю заказов для клиента")
    public List<OrderDTO> getCustomerHistoryById(@PathVariable("customer_id") Long id) {
        return customerService.getOrdersByCustomerId(id);
    }

    @PostMapping("/{customer_id}/create")
    @Operation(description = "Контроллер для создания заказа")
    public String createOrder(@PathVariable("customer_id") Long id, @RequestBody OrderDTO orderDTO) throws JsonProcessingException {
        return "Заказ создан под id " + customerService.createOrder(id, orderDTO);
    }
}
