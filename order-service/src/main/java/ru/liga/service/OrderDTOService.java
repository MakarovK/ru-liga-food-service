package ru.liga.service;

import ru.liga.dto.orders.OrderDTO;
import ru.liga.dto.orders.OrderResponseDTO;

public class OrderDTOService {
    public OrderDTO findById(OrderResponseDTO orderResponseDTO, Long id) {
        return orderResponseDTO.getOrderDTOList().stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
