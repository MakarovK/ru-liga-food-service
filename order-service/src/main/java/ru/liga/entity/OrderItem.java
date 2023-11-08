package ru.liga.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Класс-сущность, представляющий элемент заказа.
 * Элемент заказа содержит информацию о блюде из меню ресторана,
 * цене и количестве единиц этого блюда в заказе.
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "order_items")
public class OrderItem {
    /**
     * Уникальный идентификатор элемента заказа.
     *
     * @return Уникальный идентификатор элемента заказа.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Блюдо из меню ресторана, которое включено в заказ.
     *
     * @return Уникальный идентификатор блюда из меню ресторана.
     */
    @JoinColumn(name = "restaurant_menu_item_id")
    private Long restaurantMenuItemId;

    /**
     * Цена элемента заказа.
     *
     * @return Цена элемента заказа.
     */
    @Column(name = "price")
    private Integer price;

    /**
     * Количество единиц данного блюда в заказе.
     *
     * @return Количество единиц данного блюда в заказе.
     */
    @Column(name = "quantity")
    private Integer quantity;
}
