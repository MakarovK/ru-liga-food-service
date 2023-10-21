package ru.liga;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class KitchenService {
    public static void main(String[] args) {
        SpringApplication.run(KitchenService.class, args);
    }
}
