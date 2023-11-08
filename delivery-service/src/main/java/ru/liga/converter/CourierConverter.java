package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.CourierDTO;
import ru.liga.entity.Courier;

@Component
public class CourierConverter {
    public static CourierDTO entityToDto(Courier entity) {
        return new CourierDTO()
                .setId(entity.getId())
                .setPhone(entity.getPhone())
                .setStatus(entity.getStatus())
                .setCoordinates(entity.getCoordinates());
    }
    public static Courier dtoToEntity(CourierDTO courierDTO) {
        return new Courier()
                .setId(courierDTO.getId())
                .setPhone(courierDTO.getPhone())
                .setStatus(courierDTO.getStatus())
                .setCoordinates(courierDTO.getCoordinates());
    }
}
