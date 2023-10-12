package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "Order delivery")
@Data
@Accessors(chain = true)
public class OrderDTO {
    //Не знаю какие данные пока нужно ограничить по заказу
    @Schema(description = "ID ресторана")
    private Long id;

    @Schema(description = "Название ресторана")
    private String restaurantName;

    @Schema(description = "Время готовки")
    private Integer timestamp;

    @Schema(description = "Цена")
    private Double price;

    @Schema(description = "Количество")
    private Integer quantity;

    @Schema(description = "Описание продукта")
    private String description;

    @Schema(description = "Изображение продукта")
    private String imagePath;
}
