package ru.liga.rabbitmq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.liga.dto.OrderDTO;
import ru.liga.service.KitchenControllerService;


@Component
@Slf4j
public class KitchenListener {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KitchenControllerService kitchenControllerService;

    @RabbitListener(queues = "Order-queue")
    public void receiveOrder(@Payload String orderJson) {
        OrderDTO orderDTO = null;
        try {
            orderDTO = objectMapper.readValue(orderJson, OrderDTO.class);
            System.out.println("Пришёл заказ на " + orderDTO.getOrderItems() + " с ID " + orderDTO.getId());
        } catch (JsonProcessingException e) {
            log.error("Ошибка при обработке JSON", e);
        }
    }
}