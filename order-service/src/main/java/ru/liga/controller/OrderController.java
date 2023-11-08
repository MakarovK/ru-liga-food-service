package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.dto.OrderDTO;
import ru.liga.service.OrderControllerService;

import java.util.UUID;

/**
 * Контроллер для управления заказами.
 */
@RestController
@RequestMapping("/customer")
public class OrderController {
    @Autowired
    private OrderControllerService orderControllerService;

    /**
     * Получение заказа по его уникальному идентификатору.
     *
     * @param uuid Уникальный идентификатор заказа.
     * @return Ответ с данными о заказе.
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOrderById(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.getOrderById(uuid);
    }

    /**
     * Создание нового заказа для клиента.
     *
     * @param customer_id Уникальный идентификатор клиента.
     * @param orderDTO    Данные нового заказа.
     * @return Ответ с информацией о созданном заказе.
     */
    @PostMapping("/{customer_id}/create")
    public ResponseEntity<?> createOrder(@PathVariable("customer_id") Long customer_id, @RequestBody OrderDTO orderDTO) {
        return orderControllerService.createOrder(customer_id, orderDTO);
    }

    /**
     * Оплата заказа по его уникальному идентификатору.
     *
     * @param uuid Уникальный идентификатор заказа для оплаты.
     * @return Ответ с результатом оплаты заказа.
     */
    @PutMapping("/{uuid}/payment")
    public ResponseEntity<?> paymentOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.paymentOrder(uuid);
    }

    /**
     * Отклонение заказа по его уникальному идентификатору.
     *
     * @param uuid Уникальный идентификатор заказа для отклонения.
     * @return Ответ с результатом отклонения заказа.
     */
    @PutMapping("/{uuid}/deny")
    public ResponseEntity<?> denyOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.denyOrder(uuid);
    }

    /**
     * Подтверждение заказа по его уникальному идентификатору.
     *
     * @param uuid Уникальный идентификатор заказа для подтверждения.
     * @return Ответ с результатом подтверждения заказа.
     */
    @PutMapping("/{uuid}/accept")
    public ResponseEntity<?> acceptOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.acceptOrder(uuid);
    }

    /**
     * Завершение заказа по его уникальному идентификатору.
     *
     * @param uuid Уникальный идентификатор заказа для завершения.
     * @return Ответ с результатом завершения заказа.
     */
    @PutMapping("/{uuid}/complete")
    public ResponseEntity<?> completeOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.completeOrder(uuid);
    }

    /**
     * Отклонение заказа курьером по его уникальному идентификатору.
     *
     * @param uuid Уникальный идентификатор заказа для отклонения курьером.
     * @return Ответ с результатом отклонения заказа курьером.
     */
    @PutMapping("/{uuid}/courier-deny")
    public ResponseEntity<?> courierDenyOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.courierDenyOrder(uuid);
    }

    /**
     * Подтверждение заказа курьером по его уникальному идентификатору.
     *
     * @param uuid Уникальный идентификатор заказа для подтверждения курьером.
     * @return Ответ с результатом подтверждения заказа курьером.
     */
    @PutMapping("/{uuid}/courier-accept")
    public ResponseEntity<?> courierAcceptOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.courierAcceptOrder(uuid);
    }

    /**
     * Завершение заказа курьером по его уникальному идентификатору.
     *
     * @param uuid Уникальный идентификатор заказа для завершения курьером.
     * @return Ответ с результатом завершения заказа курьером.
     */
    @PutMapping("/{uuid}/courier-complete")
    public ResponseEntity<?> courierCompleteOrder(@PathVariable("uuid") UUID uuid) {
        return orderControllerService.courierCompleteOrder(uuid);
    }
}
