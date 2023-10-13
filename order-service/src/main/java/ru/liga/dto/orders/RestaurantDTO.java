package ru.liga.dto.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(description = "Класс ресторана")
@Data
public class RestaurantDTO {
    @Schema(description = "Название ресторана")
    private String name;

    public RestaurantDTO() {
    }

    public RestaurantDTO(String name) {
        this.name = name;
    }
}
