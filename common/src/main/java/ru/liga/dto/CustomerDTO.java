package ru.liga.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerDTO {
    private Long id;
    private String phone;
    private String email;
    private String address;
}
