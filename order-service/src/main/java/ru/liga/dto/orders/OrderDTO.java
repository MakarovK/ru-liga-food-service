package ru.liga.dto.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(description = "Order delivery")
@Data
@Accessors(chain = true)
public class OrderDTO {

    @Schema(description = "ID ресторана")
    private Long id;

    @Schema(description = "Название ресторана")
    private RestaurantDTO restaurant;

    @Schema(description = "Время готовки")
    private String timestamp;

    @Schema(description = "Список предметов в заказе")
    private List<ItemDTO> items;

    @Schema(description = "Enum статус заказа")
    private String status; //Это enum
}
