package ru.liga.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Order;
import ru.liga.enums.CourierStatus;
import ru.liga.enums.OrderStatus;
import ru.liga.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    public void paymentOrder(OrderDTO orderDTO) {
        String orderJson = null;
        try {
            orderJson = objectMapper.writeValueAsString(orderDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MessageProperties properties = new MessageProperties();
        properties.setHeader("message-type", "message-for-kitchen");
        Message message = new Message(orderJson.getBytes(), properties);
        rabbitTemplate.send("Notification-queue", message);
    }

    public void denyOrder(OrderDTO orderDTO) {
        String orderJson = null;
        try {
            orderJson = objectMapper.writeValueAsString(orderDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MessageProperties properties = new MessageProperties();
        properties.setHeader("message-type", "message-for-customer");
        Message message = new Message(orderJson.getBytes(), properties);
        rabbitTemplate.send("Notification-queue", message);
    }

    public void acceptOrder(OrderDTO orderDTO) {
        String orderJson = null;
        try {
            orderJson = objectMapper.writeValueAsString(orderDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MessageProperties properties = new MessageProperties();
        properties.setHeader("message-type", "message-for-customer");
        Message message = new Message(orderJson.getBytes(), properties);
        rabbitTemplate.send("Notification-queue", message);
    }

    public void completeOrder(OrderDTO orderDTO) {
        String orderJson = null;
        try {
            orderJson = objectMapper.writeValueAsString(orderDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MessageProperties properties = new MessageProperties();
        properties.setHeader("message-type", "message-for-customer");
        Message message = new Message(orderJson.getBytes(), properties);
        rabbitTemplate.send("Notification-queue", message);
    }

    public void sendMessage(String queueName, String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

    public void sendMessageForDelivery(String message, List<CourierDTO> couriersDTO, UUID order_id) {
        String replyToQueue = "Delivery-queue-response";
        Order order = orderRepository.findByUUID(order_id);
        boolean orderAccepted = false;

        for (CourierDTO courierDTO : couriersDTO) {
            if (courierDTO.getStatus().equals(CourierStatus.ACTIVE)) {
                String queueName = "Delivery-queue-courier" + courierDTO.getId();
                log.info("Отправка сообщения в очередь " + queueName);
                rabbitTemplate.convertAndSend(queueName, message);
                log.info("Отправлен запрос на доставку для курьера {} - ждём ответ", courierDTO.getId());
                Message response = rabbitTemplate.receive(replyToQueue, 40_000);
                if (response != null) {
                    String responseBody = new String(response.getBody());
                    if (responseBody.equals("ACCEPT")) {
                        orderAccepted = true;
                        order.setCourierId(courierDTO.getId());
                        order.setStatus(OrderStatus.DELIVERY_DELIVERING);
                        orderRepository.save(order);
                        log.info("Заказ принят курьером {}", courierDTO.getId());
                        break;
                    }
                }
            }
        }

        if (!orderAccepted) {
            log.warn("Курьер не найден");
        }
    }
}
