package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.OrderConverter;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.enums.OrderStatus;
import ru.liga.log.Loggable;
import ru.liga.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class KitchenService {
    @Autowired
    private OrderRepository orderRepository;
    @Loggable
    public List<OrderDTO> getAll() {
        return orderRepository.findAll().stream()
                .map(OrderConverter::entityToDto)
                .collect(Collectors.toList());
    }
    @Loggable
    public List<OrderDTO> getAllPreparingOrders(Long restaurant_id) {
        return orderRepository.findAllByRestaurantIdAndStatus(restaurant_id ,OrderStatus.PREPARING).stream()
                .map(OrderConverter::entityToDto)
                .collect(Collectors.toList());
    }
    @Loggable
    public List<OrderDTO> getALLCreatedOrders(Long restaurant_id) {
        return orderRepository.findAllByRestaurantIdAndStatus(restaurant_id ,OrderStatus.CREATED).stream()
                .map(OrderConverter::entityToDto)
                .collect(Collectors.toList());
    }
    /**
     * Метод для получение CREATED заказов
     * Когда заказ PREPARING, ищу курьера, нахожу курьера ->
     */
    @Loggable
    private Order getOrderByIdAndRestaurantId(Long id, Long restaurant_id) {
        return orderRepository.findByIdAndRestaurantId(id, restaurant_id);
    }
    @Loggable
    public void acceptOrder(Long id, Long restaurant_id) {
        orderRepository.save(getOrderByIdAndRestaurantId(id, restaurant_id).setStatus(OrderStatus.PREPARING));

    }
    @Loggable
    public void rejectOrder(Long id, Long restaurant_id) {
        orderRepository.save(getOrderByIdAndRestaurantId(id, restaurant_id).setStatus(OrderStatus.DENIED));
    }
}
