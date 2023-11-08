package ru.liga.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.liga.converter.CustomerConverter;
import ru.liga.converter.OrderConverter;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrderItemDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.enums.KitchenStatus;
import ru.liga.enums.OrderStatus;
import ru.liga.feign.CourierFeign;
import ru.liga.feign.KitchenFeign;
import ru.liga.rabbitmq.producer.MessageSender;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderControllerService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KitchenFeign kitchenFeign;

    @Autowired
    private CourierFeign courierFeign;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private MessageSender messageSender;

    /**
     * Получить заказ по его Id.
     *
     * @param uuId Id заказа.
     * @return Ответ с данными о заказе.
     */
    public ResponseEntity<?> getOrderById(UUID uuId) {
        try {
            log.info("Запрошен заказ с ID: {}", uuId);
            Order order = orderRepository.findByUUID(uuId);
            if (order != null) {
                log.info("Заказ найден: {}", orderConverter.entityToDto(order));
                return ResponseEntity.ok(orderConverter.entityToDto(order));
            } else {
                log.warn("Заказ с ID {} не найден.", uuId);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            log.error("Произошла ошибка: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка: " + ex.getMessage());
        }
    }

    /**
     * Получить ближайший ресторан к клиенту.
     *
     * @param customerId Idклиента.
     * @return Данные о ближайшем ресторане.
     */
    public RestaurantDTO getNearestRestaurant(Long customerId) {
        try {
            List<RestaurantDTO> restaurants = kitchenFeign.getRestaurants();
            Customer customer = customerRepository.findById(customerId).orElse(null);

            if (customer == null) {
                log.error("Клиент с ID " + customerId + " не найден.");
                return null;
            }

            String customerCoordinates = customer.getCoordinates();
            RestaurantDTO nearestRestaurant = null;
            double minDistance = Double.MAX_VALUE;

            for (RestaurantDTO restaurant : restaurants) {
                if (restaurant.getKitchenStatus().equals(KitchenStatus.OPEN)) {
                    double distance = DistanceService.calculateDistance(customerCoordinates, restaurant.getCoordinates());
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestRestaurant = restaurant;
                    }
                }
            }
            if (nearestRestaurant == null) {
                throw new IllegalArgumentException("Не удалось найти ближайший ресторан.");
            }

            return nearestRestaurant;
        } catch (Exception ex) {
            log.error("Ошибка при поиске ближайшего ресторана: " + ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Рассчитать стоимость заказа.
     *
     * @param orderItemDTO Элемент заказа.
     * @return Стоимость элемента заказа.
     */
    private Integer calculatePrice(OrderItemDTO orderItemDTO) {
        return kitchenFeign.getPriceByRestaurantMenuItemId(orderItemDTO.getRestaurantMenuItemId()) * orderItemDTO.getQuantity();
    }

    /**
     * Создать новый заказ для клиента.
     *
     * @param Id       Idклиента.
     * @param orderDTO Данные нового заказа.
     * @return Ответ с информацией о созданном заказе.
     */
    public ResponseEntity<?> createOrder(Long Id, OrderDTO orderDTO) {
        try {
            List<OrderItemDTO> orderItems = orderDTO.getOrderItems();
            for (int i = 0; i < orderItems.size(); i++) {
                OrderItemDTO item = orderItems.get(i);
                item.setPrice(calculatePrice(item));
            }
            UUID orderId = UUID.randomUUID();
            orderDTO.setId(orderId)
                    .setCustomer(CustomerConverter.entityToDto(customerRepository.findById(Id).orElse(null)))
                    .setCourier(null)
                    .setStatus(OrderStatus.CUSTOMER_CREATED)
                    .setRestaurant(getNearestRestaurant(Id))
                    .setOrderItems(orderItems);
            log.info("Создан новый заказ с ID: {}", orderId);
            Order order = orderRepository.save(orderConverter.dtoToEntity(orderDTO));
            log.info("Заказ успешно сохранен в базе данных с ID: {}", order.getId());
            log.info("Price: {}", orderItems.get(0).getPrice());
            return ResponseEntity.ok("Заказ успешно создан с ID: " + order.getId());
        } catch (Exception ex) {
            log.error("Ошибка при создании заказа: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при создании заказа: " + ex.getMessage());
        }
    }

    /**
     * Оплатить заказ по его Id.
     *
     * @param uuId Idзаказа для оплаты.
     * @return Ответ с результатом оплаты заказа.
     */
    public ResponseEntity<?> paymentOrder(UUID uuId) {
        try {
            Order order = orderRepository.findByUUID(uuId);

            if (order == null) {
                log.error("Заказ с UUID " + uuId + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }

            if (order.getStatus().equals(OrderStatus.CUSTOMER_PAID)) {
                log.info("Заказ с UUID " + uuId + " уже оплачен.");
                return ResponseEntity.ok("Заказ уже был оплачен");
            } else if (!order.getStatus().equals(OrderStatus.CUSTOMER_CREATED)) {
                log.error("Нельзя оплатить заказ с UUID " + uuId + " в данном состоянии: " + order.getStatus());
                return ResponseEntity.ok("Нельзя оплатить заказ в данном состоянии");
            } else {
                order.setStatus(OrderStatus.CUSTOMER_PAID);
                orderRepository.save(order);
                messageSender.paymentOrder(orderConverter.entityToDto(order));
                log.info("Заказ с UUID " + uuId + " успешно оплачен.");
                return ResponseEntity.ok("Заказ успешно оплачен");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ с UUID " + uuId + " не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при оплате заказа с UUID " + uuId + ": " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при оплате заказа: " + ex.getMessage());
        }
    }

    /**
     * Отклонить заказ по его Id.
     *
     * @param uuId Idзаказа для отклонения.
     * @return Ответ с результатом отклонения заказа.
     */
    public ResponseEntity<?> denyOrder(UUID uuId) {
        try {
            Order order = orderRepository.findByUUID(uuId);

            if (order == null) {
                log.error("Заказ с UUID " + uuId + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }
            if (order.getStatus().equals(OrderStatus.KITCHEN_DENIED)) {
                log.info("Заказ с UUID " + uuId + " уже отклонён.");
                return ResponseEntity.ok("Заказ уже был отклонён кухней ранее");
            } else if (!order.getStatus().equals(OrderStatus.CUSTOMER_PAID)) {
                log.error("Заказ с UUID " + uuId + " не может быть отклонен, так как его статус не 'CUSTOMER_PAID'.");
                return ResponseEntity.ok("На данный момент нельзя отклонить заказ");
            } else {
                order.setStatus(OrderStatus.KITCHEN_DENIED);
                orderRepository.save(order);
                messageSender.denyOrder(orderConverter.entityToDto(order));
                log.info("Заказ с UUID " + uuId + " был отклонен.");
                return ResponseEntity.ok("Заказ отклонен");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при отклонении заказа: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при отклонении заказа: " + ex.getMessage());
        }
    }

    /**
     * Подтвердить заказ по его Id.
     *
     * @param uuId Idзаказа для подтверждения.
     * @return Ответ с результатом подтверждения заказа.
     */
    public ResponseEntity<?> acceptOrder(UUID uuId) {
        try {
            Order order = orderRepository.findByUUID(uuId);

            if (order == null) {
                log.error("Заказ с UUID " + uuId + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }
            if (order.getStatus().equals(OrderStatus.KITCHEN_ACCEPTED)) {
                log.info("Заказ с UUID " + uuId + " уже принят.");
                return ResponseEntity.ok("Заказ уже был принят кухней ранее");
            } else if (!order.getStatus().equals(OrderStatus.CUSTOMER_PAID)) {
                log.error("Заказ с UUID " + uuId + " не может быть принят, так как его статус не 'CUSTOMER_PAID'.");
                return ResponseEntity.ok("На данный момент нельзя принять заказ");
            } else {
                order.setStatus(OrderStatus.KITCHEN_ACCEPTED);
                orderRepository.save(order);
                messageSender.acceptOrder(orderConverter.entityToDto(order));
                log.info("Заказ с UUID " + uuId + " был успешно принят в работу.");
                return ResponseEntity.ok("Заказ успешно принят в работу");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при принятии заказа: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при принятии заказа: " + ex.getMessage());
        }
    }


    public ResponseEntity<?> completeOrder(UUID uuid) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Order order = orderRepository.findByUUID(uuid);

            if (order == null) {
                log.error("Заказ с UUID " + uuid + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }
            if (order.getStatus().equals(OrderStatus.KITCHEN_COMPLETE)) {
                log.info("Заказ с UUID " + uuid + " уже готов.");
                return ResponseEntity.ok("Заказ уже готов, ищем курьера");
            } else if (!order.getStatus().equals(OrderStatus.KITCHEN_ACCEPTED)) {
                log.error("Заказ с UUID " + uuid + " нельзя сготовить, так как его не приняли.");
                return ResponseEntity.ok("Нельзя сготовить заказ в состоянии отличном от принят");
            } else {
                order.setStatus(OrderStatus.KITCHEN_COMPLETE);
                orderRepository.save(order);
                messageSender.completeOrder(orderConverter.entityToDto(order));
                messageSender.sendMessageForDelivery(objectMapper.writeValueAsString(orderConverter.entityToDto(order))
                        , courierFeign.getAllCouriers(), order.getId());
                log.info("Заказ с UUID " + uuid + " был приготовлен.");
                return ResponseEntity.ok("Заказ приготовлен");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при приготовлении заказа: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла приготовлении заказа: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> courierDenyOrder(UUID uuid) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Order order = orderRepository.findByUUID(uuid);

            if (order == null) {
                log.error("Заказ с UUID " + uuid + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }
            if (order.getStatus().equals(OrderStatus.DELIVERY_DENIED)) {
                log.info("Заказ с UUID " + uuid + " отклонён курьерской службой");
                return ResponseEntity.ok("Заказ отклонён курьерской службой");
            } else if (!order.getStatus().equals(OrderStatus.KITCHEN_COMPLETE)) {
                log.error("Заказ с UUID " + uuid + " нельзя отказаться, заказ ещё не готов.");
                return ResponseEntity.ok("Нельзя отказаться от заказа, он не готов");
            } else {
                order.setStatus(OrderStatus.DELIVERY_DENIED);
                orderRepository.save(order);
                messageSender.sendMessage("Customer-queue", objectMapper.writeValueAsString(orderConverter.entityToDto(order)));
                log.info("Заказ с UUID " + uuid + " был отклонён курьером " + order.getCourierId());
                return ResponseEntity.ok("Заказ был отклонён курьером");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при взятии заказа в доставку: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при взятии заказа в доставку: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> courierAcceptOrder(UUID uuid) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Order order = orderRepository.findByUUID(uuid);

            if (order == null) {
                log.error("Заказ с UUID " + uuid + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }

            if (order.getStatus().equals(OrderStatus.DELIVERY_DELIVERING)) {
                log.info("Заказ с UUID " + uuid + " уже в доставке");
                return ResponseEntity.ok("Заказ уже в доставке");
            } else if (!order.getStatus().equals(OrderStatus.DELIVERY_DELIVERING)) {
                log.error("Заказ с UUID " + uuid + " нельзя принять т.к. заказ еще не готов.");
                return ResponseEntity.ok("Нельзя принять заказ, он не готов");
            } else {
                order.setStatus(OrderStatus.DELIVERY_DELIVERING);
                orderRepository.save(order);
                messageSender.sendMessage("Customer-queue", objectMapper.writeValueAsString(orderConverter.entityToDto(order)));
                log.info("Заказ с UUID " + uuid + " был взят на доставку курьером " + order.getCourierId());
                return ResponseEntity.ok("Заказ в доставке");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при взятии заказа в доставку: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при взятии заказа в доставку: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> courierCompleteOrder(UUID uuid) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Order order = orderRepository.findByUUID(uuid);

            if (order == null) {
                log.error("Заказ с UUID " + uuid + " не найден.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
            }

            if (order.getStatus().equals(OrderStatus.DELIVERY_COMPLETE)) {
                log.info("Заказ с UUID " + uuid + " уже завершён");
                return ResponseEntity.ok("Заказ уже завершён");
            } else if (!order.getStatus().equals(OrderStatus.DELIVERY_DELIVERING)) {
                log.error("Заказ с UUID " + uuid + " нельзя завершить заказ, он не в доставке.");
                return ResponseEntity.ok("Нельзя завершить заказ, он не в доставке.");
            } else {
                order.setStatus(OrderStatus.DELIVERY_COMPLETE);
                orderRepository.save(order);
                messageSender.sendMessage("Customer-queue", objectMapper.writeValueAsString(orderConverter.entityToDto(order)));
                log.info("Заказ с UUID " + uuid + " доставлен курьером " + order.getCourierId());
                return ResponseEntity.ok("Заказ доставлен");
            }
        } catch (EntityNotFoundException ex) {
            log.error("Заказ не найден: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказ не найден");
        } catch (Exception ex) {
            log.error("Ошибка при взятии заказа в доставку: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при взятии заказа в доставку: " + ex.getMessage());
        }
    }
}
