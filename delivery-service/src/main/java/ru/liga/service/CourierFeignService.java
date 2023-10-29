package ru.liga.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourierFeignService {
    @Autowired
    private RabbitMQDeliveryServiceImpl rabbitMQDeliveryService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DeliveryOrderListener deliveryOrderListener;

    public void acceptOrder(Long courier_id, Long order_id) {
        if (courier_id == 1) {
            rabbitMQDeliveryService.sendMessage("ACCEPT");
            System.out.println("Заказ принят курьеом № 1");
        }
        if (courier_id == 2) {
            rabbitMQDeliveryService.sendMessage("ACCEPT");
            System.out.println("Заказ принят курьеом № 2");
        }
    }
}
