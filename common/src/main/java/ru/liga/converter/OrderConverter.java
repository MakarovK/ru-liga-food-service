package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;

@Component
public class OrderConverter {
    public static OrderDTO entityToDto(Order entity) {
        return new OrderDTO()
                .setId(entity.getId())
                .setCustomer(entity.getCustomer())
                .setRestaurant(entity.getRestaurant())
                .setStatus(entity.getStatus())
                .setCourier(entity.getCourier())
                .setTimestamp(entity.getTimestamp());
    }

    public static Order dtoToEntity(OrderDTO orderDTO) {
        return new Order()
                .setId(orderDTO.getId())
                .setCustomer(orderDTO.getCustomer())
                .setRestaurant(orderDTO.getRestaurant())
                .setStatus(orderDTO.getStatus())
                .setCourier(orderDTO.getCourier())
                .setTimestamp(orderDTO.getTimestamp());
    }
}
