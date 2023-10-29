package ru.liga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DeliveryService {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryService.class, args);
    }
}
