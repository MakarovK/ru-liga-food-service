package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.CourierConverter;
import ru.liga.dto.CourierDTO;
import ru.liga.repository.CourierRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourierService {

    @Autowired
    private CourierRepository courierRepository;

    public List<CourierDTO> getAllCouriers() {
        return courierRepository.findAll().stream()
                .map(CourierConverter::entityToDto)
                .collect(Collectors.toList());
    }
}
