package ru.liga.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UuidGenerator;
import ru.liga.enums.OrderStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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
     *
     * @return Уникальный идентификатор заказа.
     */
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    /**
     * Клиент, оформивший заказ.
     *
     * @return Клиент, оформивший заказ.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * Ресторан, из которого сделан заказ.
     *
     * @return Ресторан, из которого сделан заказ.
     */
    @JoinColumn(name = "restaurant_id")
    private Long restaurantId;

    /**
     * Статус заказа.
     *
     * @return Статус заказа.
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * Список позиций в заказе.
     *
     * @return Список позиций в заказе.
     */
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    /**
     * Курьер, доставляющий заказ.
     *
     * @return Курьер, доставляющий заказ.
     */
    @JoinColumn(name = "courier_id")
    private Long courierId;

    /**
     * Время создания заказа.
     *
     * @return Время создания заказа.
     */
    @Column(name = "timestamp")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}
