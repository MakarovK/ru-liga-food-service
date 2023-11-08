package ru.liga.entity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Класс-сущность, представляющий клиента.
 * Клиент содержит информацию о своем идентификаторе, телефоне, email и адресе.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "customers")
public class Customer {
    /**
     * Уникальный идентификатор клиента.
     *
     * @return Уникальный идентификатор клиента.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Телефон клиента.
     *
     * @return Телефон клиента.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Email клиента.
     *
     * @return Email клиента.
     */
    @Column(name = "email")
    private String email;

    /**
     * Адрес клиента.
     *
     * @return Адрес клиента.
     */
    @Column(name = "address")
    private String address;

    /**
     * Координаты клиента.
     *
     * @return Координаты клиента.
     */
    @Column(name = "coordinates")
    private String coordinates;
}
