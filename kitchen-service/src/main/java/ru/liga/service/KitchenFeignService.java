package ru.liga.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.CourierConverter;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Courier;
import ru.liga.entity.Order;
import ru.liga.entity.Restaurant;
import ru.liga.enums.CourierStatus;
import ru.liga.feign.KitchenFeign;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KitchenFeignService {

    @Autowired
    private KitchenFeign kitchenFeign;

    @Autowired
    private RabbitMQKitchenServiceImpl rabbitMQKitchenService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<OrderDTO> getAllPreparingOrders(Long restaurant_id) {
        return kitchenFeign.getAllPreparingOrders(restaurant_id);
    }

    public List<OrderDTO> getAllCreatedOrders(Long restaurant_id) {
        return kitchenFeign.getAllCreatedOrders(restaurant_id);
    }

    public String def() {
        return kitchenFeign.def();
    }

    private String jsonOrder(Long id) {
        Order orderDeliveryRequest = orderRepository.findById(id).orElse(null);
        try {
            return objectMapper.writeValueAsString(orderDeliveryRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptOrder(Long id, Long restaurant_id) {
        kitchenFeign.acceptOrder(id, restaurant_id);
        String jsonOrder = jsonOrder(id);
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).orElse(null);
        List<Courier> couriersForDelivery = courierRepository.findAll().stream()
                .filter(courier -> courier.getStatus().equals(CourierStatus.ACTIVE))
                .collect(Collectors.toList());
        couriersForDelivery.sort((courier1, courier2) -> (int) (DistanceService.calculateDistance(courier1.getCoordinates(), restaurant.getCoordinates())
                - DistanceService.calculateDistance(courier2.getCoordinates(), restaurant.getCoordinates())));
        rabbitMQKitchenService.sendMessageForDelivery(jsonOrder, couriersForDelivery, id);


    }

    public void rejectOrder(Long id, Long restaurant_id) {
        kitchenFeign.rejectOrder(id, restaurant_id);
    }

    public void completeOrder(Long id, Long restaurant_id) {
        kitchenFeign.completeOrder(id, restaurant_id);
    }

}
