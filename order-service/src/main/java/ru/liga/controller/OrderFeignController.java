package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.entity.Order;
import ru.liga.feign.OrderFeign;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderFeignController {
    private OrderFeign orderFeign;

    @Autowired
    public OrderFeignController(OrderFeign orderFeign) {
        this.orderFeign = orderFeign;
    }

    @GetMapping("/{order_id}")
    public Order getOrderListById(@PathVariable("order_id") Long id) {
        return orderFeign.getOrderListById(id);
    }

    @GetMapping("/courier/{courier_id}")
    public List<Order> getOrderListByCourierId(@PathVariable("courier_id") Long courier_id) {
        return orderFeign.getOrderByCourierId(courier_id);
    }

    @GetMapping("/all")
    public List<Order> getAllOrderList() {
        return orderFeign.getAllOrderList();
    }

    @PostMapping("/create")
    public String postOrder(@RequestBody Order order) {
        orderFeign.postOrder(order);
        return "Заказ добавлен";
    }

    @DeleteMapping("/delete/{order_id}")
    public String deleteOrderById(@PathVariable("order_id") Long order_id) {
        orderFeign.deleteOrderById(order_id);
        return "Заказ под id = " + order_id + " удалён";
    }
}
