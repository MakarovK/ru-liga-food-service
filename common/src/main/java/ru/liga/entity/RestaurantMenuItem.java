package ru.liga.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Класс-сущность, представляющий элемент меню ресторана.
 * Элемент меню содержит информацию о наименовании блюда, его цене,
 * изображении и описании.
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "restaurant_menu_items")
public class RestaurantMenuItem {
    /**
     * Уникальный идентификатор элемента меню ресторана.
     */
    @Id
    private Long id;

    /**
     * Наименование блюда.
     */
    @Column(name = "name")
    private String name;

    /**
     * Цена блюда.
     */
    @Column(name = "price")
    private Integer price;

    /**
     * Изображение блюда.
     */
    @Column(name = "image")
    private String image;

    /**
     * Описание блюда.
     */
    @Column(name = "description")
    private String description;
}

