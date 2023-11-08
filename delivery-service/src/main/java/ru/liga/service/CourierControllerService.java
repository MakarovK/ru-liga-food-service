package ru.liga.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.CourierConverter;
import ru.liga.dto.CourierDTO;
import ru.liga.feign.OrderFeignDelivery;
import ru.liga.repository.CourierRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Сервис для управления курьерами и доставкой.
 */
@Service
@Slf4j
public class CourierControllerService {
    @Autowired
    private RabbitMQDeliveryServiceImpl rabbitMQDeliveryService;

    @Autowired
    private OrderFeignDelivery orderFeignDelivery;

    @Autowired
    private CourierRepository courierRepository;

    /**
     * Получить список всех курьеров.
     *
     * @return Возвращает список курьеров в формате List&lt;CourierDTO&gt;.
     */
    public List<CourierDTO> getAllCouriers() {
        return courierRepository.findAll().stream()
                .map(CourierConverter::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Получить информацию о курьере по его Id.
     *
     * @param id Id курьера.
     * @return Возвращает информацию о курьере в формате CourierDTO.
     */
    public CourierDTO getCourierById(Long id) {
        return CourierConverter.entityToDto(courierRepository.getById(id));
    }

    /**
     * Принять заказ и отправить подтверждение доставки.
     *
     * @param courier_id Id курьера.
     * @param uuid        Id заказа.
     * @return Возвращает строку с результатом операции.
     */
    public String acceptOrder(Long courier_id, UUID uuid) {
        if (courier_id == 1) {
            rabbitMQDeliveryService.sendMessage("ACCEPT");
            log.info("Заказ принят курьером № 1");
        }
        if (courier_id == 2) {
            rabbitMQDeliveryService.sendMessage("ACCEPT");
            log.info("Заказ принят курьером № 2");
        }
        return orderFeignDelivery.courierAcceptOrder(uuid);
    }

    /**
     * Отклонить заказ и отправить уведомление.
     *
     * @param courier_id Id курьера.
     * @param uuid        Id заказа.
     * @return Возвращает строку с результатом операции.
     */
    public String denyOrder(Long courier_id, UUID uuid) {
        if (courier_id == 1) {
            rabbitMQDeliveryService.sendMessage("DENY");
            log.info("Заказ отклонён курьером № 1");
        }
        if (courier_id == 2) {
            rabbitMQDeliveryService.sendMessage("DENY");
            log.info("Заказ отклонён курьером № 2");
        }
        return orderFeignDelivery.courierDenyOrder(uuid);
    }

    /**
     * Завершить заказ и отправить уведомление.
     *
     * @param courierId Id курьера.
     * @param uuid       Id заказа.
     * @return Возвращает строку с результатом операции.
     */
    public String completeOrder(Long courierId, UUID uuid) {
        log.info("Заказ № {} доставлен курьером {}", uuid, courierId);
        return orderFeignDelivery.courierCompleteOrder(uuid);
    }
}
