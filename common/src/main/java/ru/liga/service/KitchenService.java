package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.OrderConverter;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.enums.OrderStatus;
import ru.liga.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class KitchenService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> getAll() {
        return orderRepository.findAll().stream()
                .map(OrderConverter::entityToDto)
                .collect(Collectors.toList());
    }
    public List<OrderDTO> getAllCurrentOrders(Long id) {
        return orderRepository.findAllByRestaurantIdAndStatus(id ,OrderStatus.PREPARING).stream()
                .map(OrderConverter::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Метод для получение CREATED заказов
     * Когда заказ PREPARING, ищу курьера, нахожу курьера ->
     */

    private Order getOrderByIdAndRestaurantId(Long id, Long restaurant_id) {
        return orderRepository.findByIdAndRestaurantId(id, restaurant_id);
    }
    public void acceptOrder(Long id, Long restaurant_id) {
        orderRepository.save(getOrderByIdAndRestaurantId(id, restaurant_id).setStatus(OrderStatus.PREPARING));

    }
    public void rejectOrder(Long id, Long restaurant_id) {
        orderRepository.save(getOrderByIdAndRestaurantId(id, restaurant_id).setStatus(OrderStatus.DENIED));
    }

    public void completeOrder(Long id, Long restaurant_id) {
        orderRepository.save(getOrderByIdAndRestaurantId(id, restaurant_id).setStatus(OrderStatus.COMPLETE));
    }
}
