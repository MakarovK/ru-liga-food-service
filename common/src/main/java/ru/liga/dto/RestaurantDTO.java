package ru.liga.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.liga.enums.KitchenStatus;

@Data
@Accessors(chain = true)
public class RestaurantDTO {
    private Long id;
    private String address;
    private KitchenStatus kitchenStatus;
}
