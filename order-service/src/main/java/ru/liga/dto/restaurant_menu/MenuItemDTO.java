package ru.liga.dto.restaurant_menu;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class MenuItemDTO {
    private Integer quantity;
    private Long menu_item_id;

    public MenuItemDTO() {
    }
}
