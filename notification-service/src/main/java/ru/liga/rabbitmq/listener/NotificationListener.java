package ru.liga.rabbitmq.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.liga.dto.OrderDTO;

@Component
@Slf4j
public class NotificationListener {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "Notification-queue")
    public void receiveNotification(@Payload String orderJson, @Header("message-type") String messageType) {
        OrderDTO orderDTO = null;
        try {
            orderDTO = objectMapper.readValue(orderJson, OrderDTO.class);
        } catch (JsonProcessingException e) {
            log.error("Ошибка при обработке JSON", e);
        }
        log.info("Пришёл заказ: {} с мессейдж тайп {}", orderDTO.getId(), messageType);

        if (messageType.equals("message-for-kitchen")) {
            rabbitTemplate.convertAndSend("Order-queue", orderJson);
            log.info("Отправлено в Order-queue");
        } else if (messageType.equals("message-for-customer")) {
            rabbitTemplate.convertAndSend("Customer-queue", orderJson);
            log.info("Отправлено в Customer-queue");
        }

        // пока не трогаем
    }
}
