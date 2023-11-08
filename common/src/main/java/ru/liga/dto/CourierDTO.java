package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.liga.enums.CourierStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CourierDTO {
    private Long id;
    private String phone;
    private CourierStatus status;
    private String coordinates;
}
