package ru.liga.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.feign.CourierFeign;
import ru.liga.feign.KitchenFeign;

import java.util.stream.Collectors;

@Component
public class OrderConverter {
    @Autowired
    private KitchenFeign kitchenFeign;

    @Autowired
    private CourierFeign courierFeign;

    public OrderDTO entityToDto(Order entity) {
        OrderDTO orderDTO = new OrderDTO()
                .setId(entity.getId())
                .setCustomer(CustomerConverter.entityToDto(entity.getCustomer()))
                .setRestaurant(kitchenFeign.getRestaurantById(entity.getRestaurantId()))
                .setOrderItems(entity.getOrderItems().stream()
                        .map(OrderItemConverter::entityToDto)
                        .collect(Collectors.toList()))
                .setStatus(entity.getStatus())
                .setTimestamp(entity.getTimestamp());
        if (entity.getCourierId() == null) {
            orderDTO.setCourier(null);
        } else {
            orderDTO.setCourier(courierFeign.getCourierById(entity.getCourierId()));
        }
        return orderDTO;
    }

    public Order dtoToEntity(OrderDTO orderDTO) {
        Order order = new Order()
                .setId(orderDTO.getId())
                .setCustomer(CustomerConverter.dtoToEntity(orderDTO.getCustomer()))
                .setRestaurantId(orderDTO.getRestaurant().getId())
                .setOrderItems(orderDTO.getOrderItems().stream()
                        .map(OrderItemConverter::dtoToEntity)
                        .collect(Collectors.toList()))
                .setStatus(orderDTO.getStatus())
                .setTimestamp(orderDTO.getTimestamp());
        if (orderDTO.getCourier() == null) {
            order.setCourierId(null);
        } else {
            order.setCourierId(orderDTO.getCourier().getId());
        }
        return order;
    }
}
