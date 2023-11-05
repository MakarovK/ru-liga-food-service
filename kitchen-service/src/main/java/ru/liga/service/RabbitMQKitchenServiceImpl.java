package ru.liga.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.entity.Courier;
import ru.liga.entity.Order;
import ru.liga.enums.OrderStatus;
import ru.liga.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RabbitMQKitchenServiceImpl implements RabbitMQKitchenService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public RabbitMQKitchenServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend("Delivery-queue", routingKey, message);
    }

    @Override
    public void sendMessageForDelivery(String message, List<Courier> couriers, Long order_id) {
        String replyToQueue = "Delivery-queue-response";
        Order order = orderRepository.findById(order_id).orElse(null);
        boolean orderAccepted = false;

        for (Courier courier : couriers) {
            String queueName = "Delivery-queue-courier" + courier.getId();
            rabbitTemplate.convertAndSend(queueName, message);
            System.out.println("Отправлен запрос на доставку для курьера " + courier.getId() + " ждём ответ");
            Message response = rabbitTemplate.receive(replyToQueue, 2_000);
            if (response != null) {
                String responseBody = new String(response.getBody());
                if (responseBody.equals("ACCEPT")) {
                    orderAccepted = true;
                    order.setCourier(courier);
                    order.setStatus(OrderStatus.DELIVERY);
                    orderRepository.save(order);
                    System.out.println("Заказ принят курьером " + courier.getId());
                    break;
                }
            } if (!orderAccepted) {
                System.out.println("Курьер не найден");
                //order.setStatus(OrderStatus.DENIED);
            }
        }
    }
}
