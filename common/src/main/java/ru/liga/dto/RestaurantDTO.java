package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.liga.enums.KitchenStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestaurantDTO {
    private Long id;

    private String address;

    private List<RestaurantMenuItemDTO> restaurantMenuItems;

    private KitchenStatus kitchenStatus;

    private String coordinates;
}
