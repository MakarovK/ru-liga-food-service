package ru.liga.entity;

import lombok.*;
import lombok.experimental.Accessors;
import ru.liga.enums.CourierStatus;

import javax.persistence.*;

/**
 * Класс-сущность, представляющий курьера.
 * Курьер содержит информацию о своем идентификаторе, телефоне, статусе и координатах.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "couriers")
public class Courier {
    /**
     * Уникальный идентификатор курьера.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Телефон курьера.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Статус курьера.
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CourierStatus status;

    /**
     * Координаты курьера.
     */
    @Column(name = "coordinates")
    private String coordinates;
}

