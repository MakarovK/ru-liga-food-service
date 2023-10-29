package ru.liga.config;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liga.entity.Order;

@Component
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    public void createOrder(Order order) throws JsonProcessingException {
        String orderJson = objectMapper.writeValueAsString(order);
        rabbitTemplate.convertAndSend("Order-queue", orderJson);
    }
}
