package ru.liga.service;

import ru.liga.dto.orders.OrderDTO;
import ru.liga.dto.orders.OrderResponseDTO;

public class OrderDTOService {
    public static void createNewOrder(OrderDTO orderDTO) {
        OrderResponseDTO.getOrderResponseDTO()
                .getOrderDTOList()
                .add(orderDTO
                        .setId((long) (OrderResponseDTO
                                .getOrderResponseDTO()
                                .getOrderDTOList().size()+1)));
    }
}
