package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.entity.Customer;
import ru.liga.feign.CoreFeign;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerFeignController {
    private CoreFeign coreFeign;

    @Autowired
    public CustomerFeignController(CoreFeign coreFeign) {
        this.coreFeign = coreFeign;
    }

//create создать новый заказ
    //showHistory посмотреть историю заказов
}
