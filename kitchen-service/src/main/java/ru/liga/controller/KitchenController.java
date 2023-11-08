package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.service.KitchenControllerService;

import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 * Контроллер для взаимодействия с сервисом кухни.
 */
@RestController
@RequestMapping("/kitchen")
public class KitchenController {

    private final KitchenControllerService kitchenControllerService;

    @Autowired
    public KitchenController(KitchenControllerService kitchenControllerService) {
        this.kitchenControllerService = kitchenControllerService;
    }

    @GetMapping("/restaurant-menu-items/{id}")
    public ResponseEntity<?> getRestaurantItemById(@PathVariable("id") @Positive Long id) {
        return kitchenControllerService.getRestaurantMenuItemById(id);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable("id") @Positive Long id) {
        return kitchenControllerService.getRestaurantById(id);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<?> getRestaurants() {
        return kitchenControllerService.getRestaurants();
    }

    @GetMapping("/restaurant-menu-items/{id}/price")
    public ResponseEntity<?> getPrice(@PathVariable("id") Long id) {
        return kitchenControllerService.getPriceByRestaurantMenuItemId(id);
    }

    @PutMapping("/restaurant/{uuid}/accept")
    public ResponseEntity<?> acceptOrder(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(kitchenControllerService.acceptOrder(uuid));
    }

    @PutMapping("/restaurant/{uuid}/deny")
    public ResponseEntity<?> denyOrder(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(kitchenControllerService.denyOrder(uuid));
    }

    @PutMapping("/restaurant/{uuid}/complete")
    public ResponseEntity<?> completeOrder(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(kitchenControllerService.completeOrder(uuid));
    }
}
