package ru.liga.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public void sendMessageCourier(String queueName, String routingKey, String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

    public void sendMessageForDelivery(String message, List<CourierDTO> couriersDTO, UUID order_id) {
        String replyToQueue = "Delivery-queue-response";
        Order order = orderRepository.findByUUID(order_id);
        boolean orderAccepted = false;

        for (CourierDTO courierDTO : couriersDTO) {
            if (courierDTO.getStatus().equals(CourierStatus.ACTIVE)) {
                String queueName = "Delivery-queue-courier" + courierDTO.getId();
                rabbitTemplate.convertAndSend(queueName, message);
                System.out.println("Отправлен запрос на доставку для курьера " + courierDTO.getId() + " ждём ответ");
                Message response = rabbitTemplate.receive(replyToQueue, 20_000);
                if (response != null) {
                    String responseBody = new String(response.getBody());
                    if (responseBody.equals("ACCEPT")) {
                        orderAccepted = true;
                        order.setCourierId(courierDTO.getId());
                        order.setStatus(OrderStatus.DELIVERY_PICKING);
                        orderRepository.save(order);
                        System.out.println("Заказ принят курьером " + courierDTO.getId());
                        break;
                    }
                }
                if (!orderAccepted) {
                    System.out.println("Курьер не найден");
                }
            }
        }
    }
}
