package ru.liga.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.liga.dto.OrderDTO;

import java.util.List;

/**
 * Интерфейс Feign для взаимодействия с сервисом кухни.
 */
@FeignClient(name = "KitchenFeign", url = "http://localhost:8087")
public interface KitchenFeign {
    /**
     * Получение всех текущих заказов.
     *
     * @return Список объектов OrderDTO, представляющих текущие заказы.
     */
    @GetMapping("/feign-kitchen/{restaurant_id}/preparing")
    List<OrderDTO> getAllPreparingOrders(@PathVariable("restaurant_id") Long restaurant_id);

    @GetMapping("/feign-kitchen/{restaurant_id}/created")
    List<OrderDTO> getAllCreatedOrders(@PathVariable("restaurant_id") Long restaurant_id);
    /**
     * Подтверждение заказа. Изменение статуса заказа на PREPARING.
     *
     * @param id Идентификатор заказа.
     */
    @PutMapping("/feign-kitchen/{restaurant_id}/accept/{order_id}")
    void acceptOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id);

    /**
     * Отклонение заказа. Изменение статуса заказа на CANCELED.
     *
     * @param id Идентификатор заказа.
     */
    @PutMapping("/feign-kitchen/{restaurant_id}/reject/{order_id}")
    void rejectOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id);
    /**
     * Выполнение заказа. Изменение статуса заказа на COMPLETE.
     *
     * @param id Идентификатор заказа.
     */
    @PutMapping("/feign-kitchen/{restaurant_id}/complete/{order_id}")
    void completeOrder(@PathVariable("order_id") Long id, @PathVariable("restaurant_id") Long restaurant_id);


}