package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Класс DTO (Data Transfer Object)
 * Содержит информацию об идентификаторе, телефоне, email и адресе клиента.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerDTO {
    /**
     * Уникальный идентификатор клиента.
     *
     * @return Уникальный идентификатор клиента.
     */
    private Long id;

    /**
     * Телефон клиента.
     *
     * @return Телефон клиента.
     */
    private String phone;

    /**
     * Email клиента.
     *
     * @return Email клиента.
     */
    private String email;

    /**
     * Адрес клиента.
     *
     * @return Адрес клиента.
     */
    private String address;
}
