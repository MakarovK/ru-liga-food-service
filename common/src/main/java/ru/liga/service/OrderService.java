package ru.liga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.repository.OrderRepository;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;



}
