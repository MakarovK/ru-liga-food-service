package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.liga.DAO.OrderListDAO;
import ru.liga.entity.OrderList;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderListController {
    private OrderListDAO orderListDAO;
    @Autowired
    public OrderListController(OrderListDAO orderListDAO) {
        this.orderListDAO = orderListDAO;
    }

    @GetMapping("/{id}")
    public OrderList getOrderListById(@PathVariable("id") Long id) {
        return orderListDAO.getOrderListById(id);
    }

    @GetMapping("/courier/{courier_id}")
    public List<OrderList> getOrderListByCourierId(@PathVariable("courier_id") Long courier_id) {
        return orderListDAO.getOrderListByCourierId(courier_id);
    }

    @GetMapping("/all")
    public List<OrderList> getAllOrderList() {
        return orderListDAO.getAllOrderList();
    }

    @PostMapping("/create")
    public String postOrder(@RequestBody OrderList orderList) {
        orderListDAO.createOrder(orderList);
        return "Заказ добавлен";
    }

    @DeleteMapping("/delete/{order_id}")
    public String deleteOrderById(@PathVariable("order_id") Long order_id) {
        orderListDAO.deleteOrderById(order_id);
        return "Заказ под id = " + order_id + " удалён";
    }
}
