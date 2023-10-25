package ru.liga.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrderItemDTO;
import ru.liga.entity.Order;
import ru.liga.service.CustomerService;

import java.util.List;

@Component
public class OrderConverter {
    public static OrderDTO entityToDto(Order entity) {
        return new OrderDTO()
                .setId(entity.getId())
                .setCustomer(entity.getCustomer())
                .setRestaurant(entity.getRestaurant())
                .setOrderItems(entity.getOrder_item_id())
                .setStatus(entity.getStatus())
                .setCourier(entity.getCourier())
                .setTimestamp(entity.getTimestamp());
    }

    public static Order dtoToEntity(OrderDTO orderDTO) {
        return new Order()
                .setId(orderDTO.getId())
                .setCustomer(orderDTO.getCustomer())
                .setRestaurant(orderDTO.getRestaurant())
                .setOrder_item_id(orderDTO.getOrderItems())
                .setStatus(orderDTO.getStatus())
                .setCourier(orderDTO.getCourier())
                .setTimestamp(orderDTO.getTimestamp());
    }
}
