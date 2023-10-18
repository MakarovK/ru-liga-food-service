package ru.liga.dto.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "Синглтон для проверки в Postman")
@NoArgsConstructor
public class OrderResponseDTO {
    private List<OrderDTO> orderDTOList;
    private Integer page_index;
    private Integer page_count;

}