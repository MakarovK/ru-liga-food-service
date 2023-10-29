package ru.liga.entity;

import lombok.*;
import lombok.experimental.Accessors;
import ru.liga.enums.KitchenStatus;

import javax.persistence.*;
import java.util.List;

/**
 * Класс-сущность, представляющий ресторан.
 * Ресторан содержит информацию о его адресе, меню и текущем статусе кухни.
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "restaurants")
public class Restaurant {
    /**
     * Уникальный идентификатор ресторана.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Адрес ресторана.
     */
    @Column(name = "address")
    private String address;

    /**
     * Меню ресторана, включающее список блюд.
     */
    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<RestaurantMenuItem> restaurantMenuItems;

    /**
     * Статус кухни ресторана.
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private KitchenStatus kitchenStatus;
}