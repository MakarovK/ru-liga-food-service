package ru.liga.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.liga.entity.Order;


/**
 * Сервис слушателя RabbitMQ для обработки заказов.
 */
@Service
@RequiredArgsConstructor
public class KitchenOrderListener {

    @RabbitListener(queues = "Order-queue")
    public void recieveOrder(String orderJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(orderJson, Order.class);
        System.out.println("Пришёл заказ с id " + order.getId() + " в ресторан " + order.getRestaurant().getId() + " принять или отклонить?");
    }
}
