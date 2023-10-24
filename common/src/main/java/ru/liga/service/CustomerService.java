package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.converter.OrderConverter;
import ru.liga.dto.OrderDTO;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    private Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public List<OrderDTO> getOrdersByCustomerId(Long id) {
        return orderRepository.findAllByCustomerId(id).stream()
                .map(x -> OrderConverter.entityToDto(x)).collect(Collectors.toList());
    }
    public void createOrder(Long id, OrderDTO orderDTO) {
        orderDTO.setCustomer(getCustomerById(id));
        orderDTO.setCourier(null);
        orderRepository.save(OrderConverter.dtoToEntity(orderDTO));
    }

}
