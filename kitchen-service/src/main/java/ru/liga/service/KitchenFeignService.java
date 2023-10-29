package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.dto.OrderDTO;
import ru.liga.feign.KitchenFeign;

import java.util.List;

@Service
public class KitchenFeignService {
    @Autowired
    private KitchenFeign kitchenFeign;

    public List<OrderDTO> getAllCurrentOrders(Long restaurant_id) {
        return kitchenFeign.getAllCurrentOrders(restaurant_id);
    }

    public void acceptOrder(Long id, Long restaurant_id) {
        kitchenFeign.acceptOrder(id, restaurant_id);
    }

    public void rejectOrder(Long id, Long restaurant_id) {
        kitchenFeign.rejectOrder(id, restaurant_id);
    }

    public void completeOrder(Long id, Long restaurant_id) {
        kitchenFeign.completeOrder(id, restaurant_id);
    }

}
