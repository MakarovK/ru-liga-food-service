package ru.liga.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Класс ресторана")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Schema(description = "Название ресторана")
    private String name;
}
