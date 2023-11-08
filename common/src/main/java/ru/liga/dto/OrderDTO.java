package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.liga.enums.OrderStatus;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderDTO {
    private UUID id;
    private CustomerDTO customer;
    private RestaurantDTO restaurant;
    private OrderStatus status;
    private CourierDTO courier;
    private List<OrderItemDTO> orderItems;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

}
