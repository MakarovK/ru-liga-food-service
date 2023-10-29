package ru.liga.dto.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Класс ресторана")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    @Schema(description = "Название ресторана")
    private String name;
}
