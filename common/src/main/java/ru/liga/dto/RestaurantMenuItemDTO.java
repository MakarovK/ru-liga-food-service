package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Класс DTO (Data Transfer Object).
 * Содержит информацию об уникальном идентификаторе, названии, цене, изображении и описании позиции.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestaurantMenuItemDTO {
    /**
     * Уникальный идентификатор позиции в меню ресторана.
     *
     * @return Уникальный идентификатор позиции в меню ресторана.
     */
    private Long id;

    /**
     * Название позиции в меню ресторана.
     *
     * @return Название позиции в меню ресторана.
     */
    private String name;

    /**
     * Цена позиции в меню ресторана.
     *
     * @return Цена позиции в меню ресторана.
     */
    private Integer price;

    /**
     * Изображение.
     *
     * @return Изображение.
     */
    private String image;

    /**
     * Описание позиции в меню ресторана.
     *
     * @return Описание позиции в меню ресторана.
     */
    private String description;
}
