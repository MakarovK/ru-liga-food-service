package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.CustomerConverter;
import ru.liga.converter.OrderConverter;
import ru.liga.dto.CustomerDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrderItemDTO;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.enums.OrderStatus;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    private CustomerDTO getCustomerById(Long id) {
        return CustomerConverter.entityToDto(customerRepository.findById(id).orElse(null));
    }

    public List<OrderDTO> getOrdersByCustomerId(Long id) {
        return orderRepository.findAllByCustomerId(id).stream()
                .map(OrderConverter::entityToDto)
                .collect(Collectors.toList());
    }
    public void createOrder(Long id, OrderDTO orderDTO) {
        orderDTO.setCustomer(CustomerConverter.dtoToEntity(getCustomerById(id)));
        orderDTO.setCourier(null);
        orderDTO.setStatus(OrderStatus.CREATED);
        orderRepository.save(OrderConverter.dtoToEntity(orderDTO));
    }

}
