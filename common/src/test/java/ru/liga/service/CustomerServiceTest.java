package ru.liga.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.dto.CustomerDTO;
import ru.liga.entity.Courier;
import ru.liga.entity.Customer;
import ru.liga.enums.CourierStatus;
import ru.liga.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerById() {
        // Создаем фиктивного клиента и его DTO
        Long customerId = 1L;
        Customer fakeCustomer = new Customer(customerId, "88005332", "Krechet@mail.ru", "Кукушкина");
        CustomerDTO fakeCustomerDTO = new CustomerDTO(customerId, "88005332", "Krechet@mail.ru", "Кукушкина");

        // Настроим поведение Mock объекта customerRepository
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(fakeCustomer));

        // Вызываем метод, который мы тестируем
        CustomerDTO result = customerService.getCustomerById(customerId);

        // Проверяем, что результат не пустой
        assertEquals(fakeCustomerDTO, result);

        // Проверяем, что поля соответствуют ожидаемым значениям
        assertEquals(customerId, result.getId());
        assertEquals("88005332", result.getPhone());
        assertEquals("Krechet@mail.ru", result.getEmail());
        assertEquals("Кукушкина", result.getAddress());
    }
}
