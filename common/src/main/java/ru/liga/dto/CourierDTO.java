package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.liga.enums.CourierStatus;

/**
 * Класс DTO (Data Transfer Object)
 * Содержит информацию об идентификаторе, телефоне, статусе и координатах курьера.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CourierDTO {
    /**
     * Уникальный идентификатор курьера.
     *
     * @return Уникальный идентификатор курьера.
     */
    private Long id;

    /**
     * Телефон курьера.
     *
     * @return Телефон курьера.
     */
    private String phone;

    /**
     * Статус курьера.
     *
     * @return Статус курьера.
     */
    private CourierStatus status;

    /**
     * Координаты курьера.
     *
     * @return Координаты курьера.
     */
    private String coordinates;
}
