package ru.liga.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.liga.enums.CourierStatus;

@Data
@Accessors(chain = true)
public class CourierDTO {
    private Long id;
    private String phone;
    private CourierStatus status;
    private String coordinates;
}
