package ru.liga.rabbitmq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.liga.dto.OrderDTO;
import ru.liga.enums.OrderStatus;

@Component
@Slf4j
public class OrderListener {
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "Customer-queue")
    public void receiveCustomer(@Payload String orderJson) {
        OrderDTO orderDTO = null;
        try {
            orderDTO = objectMapper.readValue(orderJson, OrderDTO.class);
            if (orderDTO.getStatus() == OrderStatus.KITCHEN_DENIED) {
                System.out.println("Ваш заказ с ID " + orderDTO.getId() + " был отклонён рестораном");
            } else if (orderDTO.getStatus() == OrderStatus.KITCHEN_ACCEPTED) {
                System.out.println("Ваш заказ с ID " + orderDTO.getId() + " начали готовить");
            } else if (orderDTO.getStatus() == OrderStatus.KITCHEN_COMPLETE) {
                System.out.println("Ваш заказ с ID " + orderDTO.getId() + " готов, ищем курьера");
            } else if (orderDTO.getStatus() == OrderStatus.DELIVERY_PICKING) {
                System.out.println("Ваш заказ с ID " + orderDTO.getId() + " в доставке");
            } else if (orderDTO.getStatus() == OrderStatus.DELIVERY_DENIED) {
                System.out.println("Для вашего заказа с ID " + orderDTO.getId() + " курьер не найден");
            } else if (orderDTO.getStatus() == OrderStatus.DELIVERY_COMPLETE) {
                System.out.println("Ваш заказ с ID " + orderDTO.getId() + " доставлен, приятного аппетита!!!");
            }
        } catch (JsonProcessingException e) {
            log.error("Ошибка при обработке JSON", e);
        }
    }
}