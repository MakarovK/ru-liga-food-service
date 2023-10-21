package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.entity.Courier;
import ru.liga.feign.CoreFeign;

@RestController
@RequestMapping("/couriers")
public class CourierFeignController {
    private CoreFeign coreFeign;

    @Autowired
    public CourierFeignController(CoreFeign coreFeign) {
        this.coreFeign = coreFeign;
    }

    //Couriers Rest Api
    @GetMapping("/{courier_id}")
    public Courier getCourierById(@PathVariable("courier_id") Long courier_id) {
        return coreFeign.getCourierById(courier_id);
    }

}
