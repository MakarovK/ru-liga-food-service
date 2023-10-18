package ru.liga.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Order {

    @Schema(description = "ID ресторана")
    @Id
    private Long id;

    @Schema(description = "Название ресторана")
    private Restaurant restaurant;

    @Schema(description = "Время готовки")
    private String timestamp;

    @Schema(description = "Список предметов в заказе")
    private List<Item> items;

    @Schema(description = "Enum статус заказа")
    private String status; //Это enum
}
