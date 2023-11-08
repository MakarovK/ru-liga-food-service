package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.liga.enums.OrderStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Класс DTO (Data Transfer Object).
 * Содержит информацию об уникальном идентификаторе заказа, клиенте, ресторане, статусе, курьере,
 * списках позиций в заказе и времени создания заказа.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderDTO {
    /**
     * Уникальный идентификатор заказа.
     *
     * @return Уникальный идентификатор заказа.
     */
    private UUID id;

    /**
     * Информация о клиенте, сделавшем заказ.
     *
     * @return Информация о клиенте.
     */
    private CustomerDTO customer;

    /**
     * Информация о ресторане, из которого сделан заказ.
     *
     * @return Информация о ресторане.
     */
    private RestaurantDTO restaurant;

    /**
     * Статус заказа.
     *
     * @return Статус заказа.
     */
    private OrderStatus status;

    /**
     * Информация о курьере, доставляющем заказ.
     *
     * @return Информация о курьере.
     */
    private CourierDTO courier;

    /**
     * Список позиций в заказе.
     *
     * @return Список позиций в заказе.
     */
    private List<OrderItemDTO> orderItems;

    /**
     * Время создания заказа.
     *
     * @return Время создания заказа.
     */
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}
