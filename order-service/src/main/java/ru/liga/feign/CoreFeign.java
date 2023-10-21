package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;

import java.util.List;

@FeignClient(name = "common", url = "http://localhost:8087")
public interface CoreFeign {

    //Courier API

    @GetMapping("/feign-couriers/{courier_id}")
    Courier getCourierById(@PathVariable("courier_id") Long id);

    // Customer API

    @GetMapping("/feign-customers/{customer_id}")
    Customer getCustomerById(@PathVariable("customer_id") Long id);

    @GetMapping("/feign-customers/all")
    List<Customer> getCustomers();

    // Orders API

    @GetMapping("/feign-orders/{order_id}")
    Order getOrderListById(@PathVariable("order_id") Long id);

    @GetMapping("/feign-orders/courier/{courier_id}")
    List<Order> getOrderByCourierId(@PathVariable("courier_id") Long courier_id);

    @GetMapping("/feign-orders/all")
    List<Order> getAllOrderList();

    @PostMapping("/feign-orders/create")
    String postOrder(@RequestBody Order order);

    @DeleteMapping("/feign-orders/delete/{order_id}")
    String deleteOrderById(@PathVariable("order_id") Long order_id);
}
