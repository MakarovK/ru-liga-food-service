package ru.liga.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.enums.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerId(Long customerId);
    List<Order> findAllByStatus(OrderStatus orderStatus);
    List<Order> findAllByRestaurantIdAndStatus(Long restaurantId, OrderStatus orderStatus);
    Order findByIdAndRestaurantId(Long id, Long restaurantId);
}
