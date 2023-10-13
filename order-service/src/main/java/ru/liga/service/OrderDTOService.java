package ru.liga.service;

import ru.liga.dto.orders.OrderDTO;
import ru.liga.dto.orders.OrdersDTO;

public class OrderDTOService {
    public static void createNewOrder(OrderDTO orderDTO) {
        OrdersDTO.getOrdersDTO()
                .getOrderDTOList()
                .add(orderDTO
                        .setId((long) (OrdersDTO
                                .getOrdersDTO()
                                .getOrderDTOList().size()+1)));
    }
}
