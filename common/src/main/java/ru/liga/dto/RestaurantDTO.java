package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.liga.enums.KitchenStatus;

import java.util.List;

/**
 * Класс DTO (Data Transfer Object).
 * Содержит информацию об уникальном идентификаторе, адресе, списке позиций в меню ресторана,
 * статусе кухни и координатах.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestaurantDTO {
    /**
     * Уникальный идентификатор ресторана.
     *
     * @return Уникальный идентификатор ресторана.
     */
    private Long id;

    /**
     * Адрес ресторана.
     *
     * @return Адрес ресторана.
     */
    private String address;

    /**
     * Список позиций в меню ресторана.
     *
     * @return Список позиций в меню ресторана.
     */
    private List<RestaurantMenuItemDTO> restaurantMenuItems;

    /**
     * Статус кухни ресторана.
     *
     * @return Статус кухни ресторана.
     */
    private KitchenStatus kitchenStatus;

    /**
     * Координаты ресторана.
     *
     * @return Координаты ресторана.
     */
    private String coordinates;
}
