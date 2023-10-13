package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.orders.OrderDTO;
import ru.liga.dto.orders.OrdersDTO;
import ru.liga.service.OrderDTOService;

@Tag(name = "Api для заказов")
@RestController
//@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/orders")
    @Operation(summary = "Получить все заказы")
    public OrdersDTO getAllOrders() {
        return OrdersDTO.getOrdersDTO();
    }
    @GetMapping("/order/{id}")
    @Operation(summary = "Получить заказ по ID")
    public OrderDTO getOrderById(@PathVariable("id") Long id) {
        return OrdersDTO.getOrdersDTO().findById(id);
    }
    @PutMapping("/order/update")
    @Operation(summary = "Обновить данные заказа")
    public String update(@RequestBody OrderDTO orderDTO) {
        return "Заказ изменён";
    }
    @PostMapping("/order/create")
    @Operation(summary = "Создать заказ")
    public String createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTOService.createNewOrder(orderDTO);
        return "Новый заказ создан";
    }
}

/*
    @Operation(summary = "Обновить данные заказа")
    @PutMapping("/update")
    public String updateDTO(@RequestBody OrderDTO orderDTO) {

        return "Заказ изменён";
    }

    @Operation(summary = "Создать заказ")
    @PostMapping("/create")
    private String create(@PathVariable OrderDTO orderDTO) {

        return "Новый заказ создан";
    }
he
    @Operation(summary = "Удалить доствку по ID")
    @DeleteMapping ("/delete/{id}")
    public String deleteOrderById(@PathVariable("id") Long id) {

        return "Заказ под номером " + id + " удалён";
    }
    */


