package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.service.OrderControllerService;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class OrderController {
    @Autowired
    private OrderControllerService orderControllerService;

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOrderById(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.getOrderById(uuid);
    }
    @PostMapping("/{customer_id}/create")
    public ResponseEntity<?> createOrder(@PathVariable("customer_id") Long id, @RequestBody OrderDTO orderDTO) {
        return orderControllerService.createOrder(id, orderDTO);
    }
    @PutMapping("/{uuid}/payment")
    public ResponseEntity<?> paymentOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.paymentOrder(uuid);
    }

    @PutMapping("/{uuid}/deny")
    public ResponseEntity<?> denyOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.denyOrder(uuid);
    }

    @PutMapping("/{uuid}/accept")
    public ResponseEntity<?> acceptOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.acceptOrder(uuid);
    }
    @PutMapping("/{uuid}/complete")
    public ResponseEntity<?> completeOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.completeOrder(uuid);
    }

    @PutMapping("/{uuid}/courier-deny")
    ResponseEntity<?> courierDenyOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.courierDenyOrder(uuid);
    }

    @PutMapping("/{uuid}/courier-accept")
    ResponseEntity<?> courierAcceptOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.courierAcceptOrder(uuid);
    }

    @PutMapping("/{uuid}/courier-complete")
    ResponseEntity<?> courierCompleteOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.courierCompleteOrder(uuid);
    }
}
