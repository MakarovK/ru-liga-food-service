package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderItemDTO {
    private long id;
    private Long restaurantMenuItemId;
    private Integer price;
    private Integer quantity;
}
