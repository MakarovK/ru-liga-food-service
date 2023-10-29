package ru.liga.dto.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class ItemDTO {

    @Schema(description = "Цена")
    private Double price;

    @Schema(description = "Количество")
    private Integer quantity;

    @Schema(description = "Описание продукта")
    private String description;

    @Schema(description = "Изображение продукта")
    private String image;

}
