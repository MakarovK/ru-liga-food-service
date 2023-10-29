package ru.liga.dto.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "Синглтон для проверки в Postman")
public class OrderResponseDTO {
    private List<OrderDTO> orderDTOList;
    private Integer page_index;
    private Integer page_count;

    private static OrderResponseDTO orderResponseDTO;

// Оставил просто для проверки данных и работы запросов, удалю после соединения с БД
    private OrderResponseDTO() {
        orderDTOList = new ArrayList<>();
        orderDTOList.add(new OrderDTO()
                .setId(1L)
                .setRestaurant(new RestaurantDTO("Ашан"))
                .setTimestamp("11:20")
                .setStatus("active/complete"));
        orderDTOList.add(new OrderDTO()
                .setId(2L)
                .setRestaurant(new RestaurantDTO("Спар"))
                .setTimestamp("12:20")
                .setStatus("active/complete"));
        orderDTOList.add(new OrderDTO()
                .setId(3L)
                .setRestaurant(new RestaurantDTO("Клод Моне"))
                .setTimestamp("13:20")
                .setStatus("denied"));
        orderDTOList.add(new OrderDTO()
                .setId(4L)
                .setRestaurant(new RestaurantDTO("Лента"))
                .setTimestamp("14:20")
                .setStatus("active/complete"));
        orderDTOList.add(new OrderDTO()
                .setId(5L)
                .setRestaurant(new RestaurantDTO("Абшерон"))
                .setTimestamp("15:20")
                .setStatus("denied"));
        orderDTOList.add(new OrderDTO()
                .setId(6L)
                .setRestaurant(new RestaurantDTO("Кфс"))
                .setTimestamp("16:20")
                .setStatus("active/complete"));
        page_index = 0;
        page_count = 0;
    }

    public static OrderResponseDTO getOrderResponseDTO() {
        if (orderResponseDTO == null) {
            orderResponseDTO = new OrderResponseDTO();
        }
        return orderResponseDTO;
    }

    public OrderDTO findById(Long id) {
        return orderDTOList.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
