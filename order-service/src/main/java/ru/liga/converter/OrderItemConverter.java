package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.OrderItemDTO;
import ru.liga.entity.OrderItem;

@Component
public class OrderItemConverter {
    public static OrderItemDTO entityToDto(OrderItem entity) {
        return new OrderItemDTO()
                .setId(entity.getId())
                .setRestaurantMenuItemId(entity.getRestaurantMenuItemId())
                .setPrice(entity.getPrice())
                .setQuantity(entity.getQuantity());
    }
    public static OrderItem dtoToEntity(OrderItemDTO orderItemDTO) {
        return new OrderItem()
                .setId(orderItemDTO.getId())
                .setRestaurantMenuItemId(orderItemDTO.getRestaurantMenuItemId())
                .setPrice(orderItemDTO.getPrice())
                .setQuantity(orderItemDTO.getQuantity());
    }
}