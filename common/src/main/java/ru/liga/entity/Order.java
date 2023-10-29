package ru.liga.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.liga.enums.OrderStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Класс-сущность, представляющий заказ в системе.
 * Заказ содержит информацию о клиенте, ресторане, статусе,
 * списке позиций в заказе, курьере и времени создания.
 */
@Getter
@Setter
@ToString
@Entity
@Accessors(chain = true)
@Table(name = "orders")
public class Order {
    /**
     * Уникальный идентификатор заказа.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Клиент, оформивший заказ.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    /**
     * Ресторан, из которого сделан заказ.
     */
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    /**
     * Статус заказа, PAID, CANCELED, CREATED.
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    /**
     * Список позиций в заказе.
     */
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> order_item_id;
    /**
     * Курьер, доставляющий заказ.
     */
    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
    /**
     * Время создания заказа.
     */
    @Column(name = "timestamp")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}
