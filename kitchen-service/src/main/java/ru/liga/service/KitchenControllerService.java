package ru.liga.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.converter.RestaurantConverter;
import ru.liga.converter.RestaurantMenuItemConverter;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.feign.OrderFeign;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.repository.RestaurantRepository;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления кухней ресторана.
 */
@Service
@Slf4j
public class KitchenControllerService {
    @Autowired
    private RestaurantMenuItemRepository restaurantMenuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderFeign orderFeign;

    /**
     * Получить информацию о блюде по его Id.
     *
     * @param id Id блюда.
     * @return Возвращает информацию о блюде в формате ResponseEntity.
     */
    public ResponseEntity<?> getRestaurantMenuItemById(Long id) {
        try {
            log.info("Запрошено блюдо с ID: {}", id);
            RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository.findById(id).orElse(null);
            if (restaurantMenuItem != null) {
                log.info("Блюдо найдено: {}", RestaurantMenuItemConverter.entityToDto(restaurantMenuItem));
                return ResponseEntity.ok(RestaurantMenuItemConverter.entityToDto(restaurantMenuItem));
            } else {
                log.warn("Блюдо с ID {} не найдено.", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            log.error("Произошла ошибка: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка: " + ex.getMessage());
        }
    }

    /**
     * Получить информацию о ресторане по его Id.
     *
     * @param id Id ресторана.
     * @return Возвращает информацию о ресторане в формате ResponseEntity.
     */
    public ResponseEntity<?> getRestaurantById(Long id) {
        try {
            log.info("Запрошен ресторан с ID: {}", id);
            Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
            if (restaurant != null) {
                log.info("Ресторан найден: {}", RestaurantConverter.entityToDto(restaurant));
                return ResponseEntity.ok(RestaurantConverter.entityToDto(restaurant));
            } else {
                log.warn("Ресторан с ID {} не найден.", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            log.error("Произошла ошибка: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка: " + ex.getMessage());
        }
    }

    /**
     * Получить список всех ресторанов.
     *
     * @return Возвращает список ресторанов в формате ResponseEntity.
     */
    public ResponseEntity<?> getRestaurants() {
        try {
            log.info("Запрошен список ресторанов");
            List<Restaurant> restaurants = restaurantRepository.findAll();
            if (restaurants != null) {
                log.info("Рестораны найдены: ", RestaurantConverter.listEntityToDto(restaurants));
                return ResponseEntity.ok(RestaurantConverter.listEntityToDto(restaurants));
            } else {
                log.warn("Ресторанов не найдено");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            log.error("Произошла ошибка: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка: " + ex.getMessage());
        }
    }

    /**
     * Получить цену блюда по его Id.
     *
     * @param id Id блюда.
     * @return Возвращает цену блюда в формате ResponseEntity.
     */
    public ResponseEntity<?> getPriceByRestaurantMenuItemId(Long id) {
        try {
            log.info("Запрошена цена блюда");
            RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRepository.findById(id).orElse(null);
            Integer price = restaurantMenuItem.getPrice();
            if (restaurantMenuItem != null) {
                log.info("Найдена позиция в ресторане, цена равна: {}", price);
                return ResponseEntity.ok(price);
            } else {
                log.warn("Не найдена позиция в ресторане");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            log.error("Произошла ошибка: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка: " + ex.getMessage());
        }
    }

    /**
     * Отклонить заказ по его UUID.
     *
     * @param uuid Id заказа.
     * @return Возвращает строку с результатом операции.
     */
    public String denyOrder(UUID uuid) {
        return orderFeign.denyOrder(uuid);
    }

    /**
     * Принять заказ по его UUID.
     *
     * @param uuid Id заказа.
     * @return Возвращает строку с результатом операции.
     */
    public String acceptOrder(UUID uuid) {
        return orderFeign.acceptOrder(uuid);
    }

    /**
     * Завершить заказ по его UUID.
     *
     * @param uuid Id заказа.
     * @return Возвращает строку с результатом операции.
     */
    public String completeOrder(UUID uuid) {
        return orderFeign.completeOrder(uuid);
    }
}
