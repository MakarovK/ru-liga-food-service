package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.entity.Order;
import ru.liga.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerId(Long customerId);

    List<Order> findAllByRestaurantIdAndStatus(Long restaurantId, OrderStatus orderStatus);

    Order findByIdAndRestaurantId(Long id, Long restaurantId);

    @Query("select o from Order o where o.id = :uuid")
    Order findByUUID(@Param("uuid") UUID uuid);
}

