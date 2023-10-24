package ru.liga.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.enums.OrderStatus;
import java.sql.Timestamp;
import java.util.List;

@Data
@Accessors(chain = true)
public class OrderDTO {
    private Long Id;
    private Customer customer;
    private Restaurant restaurant;
    private OrderStatus status;
    private Courier courier;
    private List<OrderItem> orderItems;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}
