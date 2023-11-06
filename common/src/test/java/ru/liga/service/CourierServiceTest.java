package ru.liga.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.liga.dto.CourierDTO;
import ru.liga.entity.Courier;
import ru.liga.enums.CourierStatus;
import ru.liga.repository.CourierRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CourierServiceTest {

    @InjectMocks
    private CourierService courierService;

    @Mock
    private CourierRepository courierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCouriers() {
        // Создаем фиктивный список курьеров
        List<Courier> fakeCouriers = new ArrayList<>();
        fakeCouriers.add(new Courier(1L, "88005332", CourierStatus.INACTIVE, "323423"));
        fakeCouriers.add(new Courier(2L, "89003332", CourierStatus.ACTIVE, "8832"));

        // Настроим поведение Mock объекта courierRepository
        Mockito.when(courierRepository.findAll()).thenReturn(fakeCouriers);

        // Вызываем метод, который мы тестируем
        List<CourierDTO> result = courierService.getAllCouriers();

        // Проверяем, что результат не пустой
        assertNotNull(result);


        assertEquals(2, result.size());


        assertEquals("88005332", result.get(0).getPhone());
        assertEquals("89003332", result.get(1).getPhone());

        assertEquals(CourierStatus.INACTIVE, result.get(0).getStatus());
        assertEquals(CourierStatus.ACTIVE, result.get(1).getStatus());

        assertEquals("323423", result.get(0).getCoordinates());
        assertEquals("8832", result.get(1).getCoordinates());
    }
}
