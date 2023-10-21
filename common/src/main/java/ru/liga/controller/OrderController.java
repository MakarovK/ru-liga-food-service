package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.DAO.OrderDAO;
import ru.liga.entity.Order;

import java.util.List;

@RestController
@RequestMapping("/feign-orders")
public class OrderController {
    private OrderDAO orderDAO;
    @Autowired
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping("/{order_id}")
    public Order getOrderListById(@PathVariable("order_id") Long id) {
        return orderDAO.getOrderListById(id);
    }

    @GetMapping("/courier/{courier_id}")
    public List<Order> getOrderListByCourierId(@PathVariable("courier_id") Long courier_id) {
        return orderDAO.getOrderByCourierId(courier_id);
    }

    @GetMapping("/all")
    public List<Order> getAllOrderList() {
        return orderDAO.getAllOrderList();
    }

    @PostMapping("/create")
    public String postOrder(@RequestBody Order order) {
        orderDAO.createOrder(order);
        return "Заказ добавлен";
    }

    @DeleteMapping("/delete/{order_id}")
    public String deleteOrderById(@PathVariable("order_id") Long order_id) {
        orderDAO.deleteOrderById(order_id);
        return "Заказ под id = " + order_id + " удалён";
    }
}
