package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.OrderItemDTO;
import ru.liga.entity.OrderItem;

@Component
public class OrderItemConverter {
    public static OrderItemDTO entityToDto(OrderItem entity) {
        return new OrderItemDTO()
                .setId(entity.getId())
                //.setOrder_id(entity.get)
                .setRestaurantMenuItem_id(entity.getRestaurantMenuItem_id())
                .setPrice(entity.getPrice())
                .setQuantity(entity.getQuantity());
    }
    public static OrderItem dtoToEntity(OrderItemDTO orderItemDTO) {
        return new OrderItem()
                .setId(orderItemDTO.getId())
                //.setOrder_id(orderItemDTO.getOrder_id())
                .setRestaurantMenuItem_id(orderItemDTO.getRestaurantMenuItem_id())
                .setPrice(orderItemDTO.getPrice())
                .setQuantity(orderItemDTO.getQuantity());
    }
}
