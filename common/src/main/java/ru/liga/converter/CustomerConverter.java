package ru.liga.converter;

import org.springframework.stereotype.Component;
import ru.liga.dto.CustomerDTO;
import ru.liga.entity.Customer;

@Component
public class CustomerConverter {
    public CustomerDTO entityToDto(Customer entity) {
        return new CustomerDTO()
                .setId(entity.getId())
                .setPhone(entity.getPhone())
                .setEmail(entity.getEmail())
                .setAddress(entity.getAddress());
    }
    public Customer dtoToEntity(CustomerDTO customerDTO) {
        return new Customer()
                .setId(customerDTO.getId())
                .setPhone(customerDTO.getPhone())
                .setEmail(customerDTO.getEmail())
                .setAddress(customerDTO.getAddress());
    }
}
