package ru.liga.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.CourierConverter;
import ru.liga.dto.CourierDTO;
import ru.liga.entity.Courier;
import ru.liga.repository.CourierRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourierControllerService {
    @Autowired
    private RabbitMQDeliveryServiceImpl rabbitMQDeliveryService;

    @Autowired
    private CourierRepository courierRepository;

    public List<CourierDTO> getAllCouriers() {
        return courierRepository.findAll().stream()
                .map(CourierConverter::entityToDto)
                .collect(Collectors.toList());
    }

    public CourierDTO getCourierById(Long id) {
        return CourierConverter.entityToDto(courierRepository.getById(id));
    }

    public void acceptOrder(Long courier_id, UUID uuid) {
        if (courier_id == 1) {
            rabbitMQDeliveryService.sendMessage("ACCEPT");
            System.out.println("Заказ принят курьером № 1");
        }
        if (courier_id == 2) {
            rabbitMQDeliveryService.sendMessage("ACCEPT");
            System.out.println("Заказ принят курьером № 2");
        }
    }
}
