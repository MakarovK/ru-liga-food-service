package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Класс DTO (Data Transfer Object).
 * Содержит информацию об уникальном идентификаторе, блюде из меню ресторана, цене
 * и количестве единиц этого блюда в заказе.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderItemDTO {
    /**
     * Уникальный идентификатор позиции заказа.
     *
     * @return Уникальный идентификатор позиции заказа.
     */
    private long id;

    /**
     * Идентификатор блюда из меню ресторана, включенного в заказ.
     *
     * @return Идентификатор блюда из меню ресторана.
     */
    private Long restaurantMenuItemId;

    /**
     * Цена данной позиции заказа.
     *
     * @return Цена позиции заказа.
     */
    private Integer price;

    /**
     * Количество единиц данного блюда в заказе.
     *
     * @return Количество единиц данного блюда в заказе.
     */
    private Integer quantity;
}
