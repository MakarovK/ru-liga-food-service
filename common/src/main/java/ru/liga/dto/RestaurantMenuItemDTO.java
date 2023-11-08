package ru.liga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestaurantMenuItemDTO {
    private Long id;
    private String name;
    private Integer price;
    private String image;
    private String description;
}
