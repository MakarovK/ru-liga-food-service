package ru.liga.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "DTO delivery")
@Data
@Accessors(chain = true)
public class DeliveryDTO {
    //Не знаю какие данные пока нужно ограничить курьеру в DTO
    @Schema(description = "Id доставки")
    private Long id;

    @Schema(description = "Адрес ресторана")
    private String restaurantAddress;

    @Schema(description = "Расстояние до ресторана")
    private Double restaurantDistance;

    @Schema(description = "Адрес клиента")
    private String customerAddress;

    @Schema(description = "Расстояние до клиента")
    private Double distanceCustomer;

    @Schema(description = "Оплата")
    private Double payment;
}
