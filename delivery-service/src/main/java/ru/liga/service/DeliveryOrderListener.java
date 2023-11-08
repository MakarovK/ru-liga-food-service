package ru.liga.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.dto.OrderDTO;


@Service
@RequiredArgsConstructor
public class DeliveryOrderListener {
    @Autowired
    private ObjectMapper objectMapper;
    @RabbitListener(queues = "Delivery-queue-courier1")
    public void receiveMessageForCourier1(String message) {
        OrderDTO order;
        try {
            order = objectMapper.readValue(message, OrderDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Пришёл заказ № " + order.getId() + " для курьера 1. Принять или отклонить заказ?" );

    }

    @RabbitListener(queues = "Delivery-queue-courier2")
    public void receiveMessageForCourier2(String message) {
        OrderDTO order;
        try {
            order = objectMapper.readValue(message, OrderDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Пришёл заказ № " + order.getId() + " для курьера 2. Принять или отклонить заказ?");
    }
}

